package console.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("T_LOBBY_WALLET_MANAGER")
public class WalletManagerNew {

    @TableId
    @TableField("wallet_id")
    private Long walletId;

    @TableField("merchant_id")
    private Long merchantId;

    @TableField("merchant_core")
    private String merchantCore;

}
