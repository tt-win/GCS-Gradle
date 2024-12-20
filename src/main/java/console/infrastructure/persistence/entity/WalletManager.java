package console.infrastructure.persistence.entity;

public class WalletManager {
    private Long walletId;
    private Long merchantId;
    private String merchantCore;

    public Long getWalletId() {
        return walletId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public String getMerchantCore() {
        return merchantCore;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantCore(String merchantCore) {
        this.merchantCore = merchantCore;
    }
}
