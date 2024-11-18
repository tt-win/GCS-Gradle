package console.infrastructure.persistence.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("T_LOBBY_WALLET_MANAGER")
public class WalletManagerCassandra {
    @PrimaryKey
    private Long id;

    @Column("account_type_id")
    private Integer accountTypeId;

    @Column("wallet_id")
    private String walletId;

}
