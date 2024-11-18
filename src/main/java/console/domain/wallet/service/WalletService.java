package console.domain.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import console.infrastructure.storage.enums.DataSourceType;
import console.infrastructure.persistence.entity.WalletManagerNew;

import console.infrastructure.storage.annotation.DataSourceStrategy;

@Service
public class WalletService {

    @Autowired
    private WalletManagerNewService walletManagerNewService;


    @DataSourceStrategy(
            key = "wallet:all:list",
            order = {DataSourceType.REDIS, DataSourceType.CASSANDRA, DataSourceType.ORACLE})
    public List<WalletManagerNew> getAllWallets() {
        List<WalletManagerNew> list = walletManagerNewService.getAllWalletManagers();
        return list;
    }


    @DataSourceStrategy(
            key = "'wallet:merchant:' + #merchantCode",
            order = {DataSourceType.REDIS, DataSourceType.CASSANDRA},
            timeout = 1 // 客製化, 不寫走預設
    )
    public List<WalletManagerNew> getWalletsByMerchantCode(@PathVariable String merchantCode) {
        return walletManagerNewService.getAllWalletManagers();
    }

    @DataSourceStrategy(
            key = "'wallet:merchant:' + #merchantCode",
            order = {DataSourceType.CAFFEINE, DataSourceType.REDIS, DataSourceType.ORACLE})
    public List<WalletManagerNew> getWalletsTest(@PathVariable String merchantCode) {
        return walletManagerNewService.getAllWalletManagers();
    }
}
