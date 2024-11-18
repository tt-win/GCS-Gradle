package console.application.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String code;           // 狀態碼
    private String message;        // 訊息
    private T data;                // 數據
    private String timestamp;      // 時間戳

    // 成功的靜態方法
    public static <T> Response<T> success(T data) {
        return new Response<>("0000", "success", data, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    // 失敗的靜態方法
    public static <T> Response<T> error(String code, String message) {
        return new Response<>(code, message, null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
