package console.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import console.mapper.WalletManagerMapper;
import console.model.WalletManager;

import java.util.List;

@Service
public class HelloService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private final WalletManagerMapper walletManagerMapper;

    public HelloService(JdbcTemplate jdbcTemplate, WalletManagerMapper walletManagerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.walletManagerMapper = walletManagerMapper;
    }

    public int getAllWalletManagers() {
        String sql = "SELECT COUNT(*) FROM T_LOBBY_WALLET_MANAGER";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int getAllWalletManagersByMybatis(){
        return walletManagerMapper.countWalletManagers();
    }

    public List<WalletManager> getWalletManagerList() {
        List<WalletManager> list = walletManagerMapper.getWalletManagerList();
        System.out.println(list.size());
        return list;
    }

}
