package console.domain.hello.entity;

import lombok.Data;

@Data
public class WalletManager {
    private Long walletId;
    private String merchantCode;

    public WalletManager(Long walletId, String merchantCode) {
        this.walletId = walletId;
        this.merchantCode = merchantCode;
    }
}
