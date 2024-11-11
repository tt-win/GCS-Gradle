package console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "測試管理", description = "初步測試功能")
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redis")
    @Operation(summary = "Redis測試", description = "測試Redis連線", tags = { "測試管理" } )
    @ApiResponse(responseCode = "200", description = "Successful redis response")
    public String testRedis() {
        try {
            System.out.println("jc test1.");
            // 測試寫入
            redisTemplate.opsForValue().set("test-key", "Hello Redis");

            System.out.println("jc test2.");
            // 讀取並返回結果
            Object value = redisTemplate.opsForValue().get("test-key");

            System.out.println("jc test3.");
            // 清理測試數據
            redisTemplate.delete("test-key");

            System.out.println("jc test4.");
            return "Redis測試成功! 值為: " + value;
        } catch (Exception e) {
            System.out.println("jc test5.");
            return "Redis測試失敗: " + e.getMessage();
        }
    }

}
