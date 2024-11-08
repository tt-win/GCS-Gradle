package console.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import console.mapper.WalletManagerMapper;
import console.model.entity.WalletManager;

import java.util.List;

@Service
public class HelloService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private final WalletManagerMapper walletManagerMapper;

    public HelloService(JdbcTemplate jdbcTemplate, WalletManagerMapper walletManagerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.walletManagerMapper = walletManagerMapper;
    }

    public List<WalletManager> getAllWalletManagers() {
        long startTime = System.currentTimeMillis();
        String sql = "SELECT wallet_id, merchant_id, merchant_core FROM T_LOBBY_WALLET_MANAGER";
        jdbcTemplate.setFetchSize(500);
        List<WalletManager> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            WalletManager manager = new WalletManager();
            manager.setWalletId(rs.getLong("wallet_id"));
            manager.setMerchantId(rs.getLong("merchant_id"));
            manager.setMerchantCore(rs.getString("merchant_core"));
            return manager;
        });

        long endTime = System.currentTimeMillis();
        System.out.println("Query executed in "+ (endTime - startTime) +" ms ,records:"+ result.size());

        return result;
    }

    public List<WalletManager> getAllWalletManagersByMybatis(){
        long startTime = System.currentTimeMillis();
        List<WalletManager> result  = walletManagerMapper.getWalletManagersBySELECT();

        long endTime = System.currentTimeMillis();
        System.out.println("Query executed in "+ (endTime - startTime) +" ms ,records:"+ result.size());

        return result;
    }

    public List<WalletManager> getWalletManagerList() {
        long startTime = System.currentTimeMillis();
        List<WalletManager> result = walletManagerMapper.getWalletManagerList();
        long endTime = System.currentTimeMillis();
        System.out.println("Query executed in "+ (endTime - startTime) +" ms ,records:"+ result.size());
        return result;
    }

}
