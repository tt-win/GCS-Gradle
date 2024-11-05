package console.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import console.model.WalletManager;

@Mapper
public interface WalletManagerMapper {

    @Select("SELECT COUNT(*) FROM T_LOBBY_WALLET_MANAGER")
    int countWalletManagers();

    List<WalletManager> getWalletManagerList();

}
