package console.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("T_LOBBY_WALLET_MANAGER")
public class WalletManagerNew {

    @TableId
    @TableField("wallet_id")
    private String walletId;

    @TableField("merchant_id")
    private String merchantId;

    @TableField("merchant_core")
    private String merchantCore;

}
