package console.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import console.domain.hello.entity.WalletManager;
import console.infrastructure.persistence.entity.WalletManagerPO;
import console.domain.wallet.repository.WalletManagerRepository;
import console.infrastructure.persistence.entity.WalletManagerNew;
import console.infrastructure.persistence.mapper.WalletManagerNewMapper;
import console.infrastructure.persistence.mapper.WalletManagerTestMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WalletManagerRepositoryImpl implements WalletManagerRepository {
    //private final WalletManagerNewMapper mapper;
    private final WalletManagerTestMapper mapper;

    @Override
    public List<WalletManager> findByMerchantCode(String merchantCode) {
        // 測試使用現有的查詢邏輯，但轉換成新的domain對象
        LambdaQueryWrapper<WalletManagerPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletManagerPO::getMerchantCore, merchantCode);

        return mapper.selectList(queryWrapper)
                     .stream()
                     .map(this::convertToWalletManager)
                     .collect(Collectors.toList());
    }

    private WalletManager convertToWalletManager(WalletManagerPO po) {
        return new WalletManager(
                po.getWalletId(),
                po.getMerchantCore()
        );
    }
}
