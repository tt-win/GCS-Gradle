package console.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data                // Lombok 注解，自動生成 getter/setter
@Builder            // 建造者模式
@NoArgsConstructor  // 無參構造函數
@AllArgsConstructor // 全參構造函數
public class WalletManagerResponse {
    private Long walletId;
    private String merchantCode;
    private BigDecimal balance;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 如果需要自定義一些計算屬性
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }
}
