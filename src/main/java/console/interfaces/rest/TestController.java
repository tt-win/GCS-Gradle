package console.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "測試管理", description = "初步測試功能")
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @GetMapping("/redis")
    @Operation(summary = "Redis測試", description = "測試Redis連線", tags = { "測試管理" } )
    @ApiResponse(responseCode = "200", description = "Successful redis response")
    public String testRedis() {
        try {
            // 寫入
            redisTemplate.opsForValue().set("test-key", "Hello Redis");

            // 讀取並返回結果
            Object value = redisTemplate.opsForValue().get("test-key");

            // 清理測試數據
            redisTemplate.delete("test-key");
            return "Redis測試成功! 值為: " + value;
        } catch (Exception e) {
            return "Redis測試失敗: " + e.getMessage();
        }
    }

    @GetMapping("/cassandra")
    @Operation(summary = "Cassandra測試", description = "測試Cassandra連線", tags = { "測試管理" })
    @ApiResponse(responseCode = "200", description = "Successful cassandra response")
    public String testCassandra() {
        try {
            StringBuilder result = new StringBuilder();
            String query = "SELECT * FROM "+"\"T_LOBBY_WALLET_MANAGER\" "+ "LIMIT 1";
            List<?> tableData = cassandraTemplate.getCqlOperations()
                                                 .queryForList(query);
            result.append("查詢結果數量: ").append(tableData.size());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Cassandra 連線測試失敗: " + e.getMessage();
        }
    }

}
