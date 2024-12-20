package console.infrastructure.storage.aspect;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import console.infrastructure.cache.handler.CassandraQueryHandler;
import console.infrastructure.persistence.entity.WalletManagerNew;
import console.infrastructure.storage.annotation.DataSourceStrategy;
import console.infrastructure.storage.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final Cache<String, Object> caffeineCache;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CassandraTemplate cassandraTemplate;
    private final CassandraQueryHandler queryHandler;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DataSourceAspect(Cache<String, Object> caffeineCache, RedisTemplate<String, Object> redisTemplate, CassandraTemplate cassandraTemplate,
                            CassandraQueryHandler queryHandler, StringRedisTemplate stringRedisTemplate) {
        this.caffeineCache = caffeineCache;
        this.redisTemplate = redisTemplate;
        this.cassandraTemplate = cassandraTemplate;
        this.queryHandler = queryHandler;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Around("@annotation(strategy)")
    public Object around(ProceedingJoinPoint point, DataSourceStrategy strategy) throws Throwable {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        String cacheKey = resolveCacheKey(strategy.key(), method, point.getArgs());
        log.info("開始查詢，key: {}", cacheKey);

        for (DataSourceType sourceType : strategy.order()) {
            Object result = fetchDataFromSource(sourceType, cacheKey, method, point);

            if (result != null) {
                cacheResult(cacheKey, result, strategy);
                return result;
            }
        }

        return point.proceed();
    }

    private String resolveCacheKey(String cacheKeyExpression, Method method, Object[] args) {
        if (cacheKeyExpression.contains("#") && method.getParameterCount() > 0) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                context.setVariable(parameters[i].getName(), args[i]);
            }
            return parser.parseExpression(cacheKeyExpression).getValue(context, String.class);
        }
        return cacheKeyExpression;
    }

    private Object fetchDataFromSource(DataSourceType sourceType, String cacheKey, Method method, ProceedingJoinPoint point) throws Throwable {
        switch (sourceType) {
            case CAFFEINE:
                return fetchFromCaffeine(cacheKey);

            case REDIS:
                return fetchFromRedis(cacheKey);

            case CASSANDRA:
                return fetchFromCassandra(method.getName(), point.getArgs());

            case ORACLE:
                return point.proceed();

            default:
                log.warn("未知的資料來源類型: {}", sourceType);
                return null;
        }
    }

    private Object fetchFromCaffeine(String cacheKey) {
        log.info("嘗試從Caffeine獲取數據");
        Object result = caffeineCache.getIfPresent(cacheKey);
        if (result != null) {
            log.info("Caffeine命中");
        }
        return result;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Object fetchFromRedis(String cacheKey) {
        log.info("嘗試從Redis獲取數據");
        try {
            String jsonString = stringRedisTemplate.opsForValue().get(cacheKey);

            if (jsonString != null) {
                log.info("Redis命中");
                return objectMapper.readValue(jsonString,
                                              new TypeReference<List<WalletManagerNew>>() {
                                              });
            }
        } catch (Exception e) {
            log.error("從Redis讀取數據時發生錯誤: {}", e.getMessage());
            return null;
        }

        return null;

    }

    private Object fetchFromCassandra(String methodName, Object[] args) {
        log.info("嘗試從Cassandra獲取數據");
        String query = queryHandler.getQuery(methodName, args);
        Object result = cassandraTemplate.getCqlOperations().queryForList(query);

        if (result != null) {
            log.info("Cassandra命中");
        }
        return result;
    }

    private void cacheResult(String cacheKey, Object result, DataSourceStrategy strategy) {
        if (result != null) {
            try{
                caffeineCache.put(cacheKey, result);
                String jsonString = objectMapper.writeValueAsString(result);

                // Redis存JSON字串
                stringRedisTemplate.opsForValue().set(
                        cacheKey,
                        jsonString,
                        strategy.timeout(),
                        strategy.timeUnit());

                log.info("結果已緩存至Caffeine和Redis，key: {}", cacheKey);
            } catch (Exception e) {
                log.error("緩存數據時發生錯誤: {}", e.getMessage());
            }
        }
    }
}
