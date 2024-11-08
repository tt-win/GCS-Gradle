package console.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import console.model.entity.WalletManager;

@Mapper
public interface WalletManagerMapper {

    @Select("SELECT wallet_id, merchant_id, merchant_core FROM T_LOBBY_WALLET_MANAGER")
    List<WalletManager> getWalletManagersBySELECT();

    List<WalletManager> getWalletManagerList();

}
