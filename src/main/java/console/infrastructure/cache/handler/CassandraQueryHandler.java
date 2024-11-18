package console.infrastructure.cache.handler;

public interface CassandraQueryHandler {
    String getQuery(String methodName, Object[] args);
}
