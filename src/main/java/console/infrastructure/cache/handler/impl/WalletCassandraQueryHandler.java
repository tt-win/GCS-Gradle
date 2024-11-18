package console.infrastructure.cache.handler.impl;

import org.springframework.stereotype.Component;

import console.infrastructure.cache.handler.CassandraQueryHandler;

@Component
public class WalletCassandraQueryHandler implements CassandraQueryHandler {
    @Override
    public String getQuery(String methodName, Object[] args) {
        switch (methodName) {
            case "getAllWallets":
                return "SELECT * FROM \"T_LOBBY_WALLET_MANAGER\"";

            case "getWalletsByMerchantCode":
                String merchantCode = (String) args[0];
                //return "SELECT * FROM \"T_LOBBY_WALLET_MANAGER\" WHERE \"MERCHANT_CODE\" = '" + merchantCode + "'";
                return "SELECT * FROM \"T_LOBBY_WALLET_MANAGER\"";

            // 其他查詢方法...
            default:
                throw new UnsupportedOperationException("未支援的方法: " + methodName);
        }
    }
}
