package console.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("T_LOBBY_WALLET_MANAGER")
public class WalletManagerPO {
    @TableId
    private Long walletId;

    @TableField("merchant_core")
    private String merchantCore;
}
