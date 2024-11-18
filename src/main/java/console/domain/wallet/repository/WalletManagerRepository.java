package console.domain.wallet.repository;

import java.util.List;

import console.domain.hello.entity.WalletManager;

public interface WalletManagerRepository {
    List<WalletManager> findByMerchantCode(String merchantCode);
}
