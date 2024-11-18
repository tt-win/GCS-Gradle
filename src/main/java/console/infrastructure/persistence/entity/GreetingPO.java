package console.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;


@Table("T_LOBBY_WALLET_MANAGER")
public class GreetingPO {
    @TableId
    private Long walletId;

    @TableField("merchant_id")
    private Long merchantId;

    @TableField("merchant_core")
    private String merchantCore;
}
