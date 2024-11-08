package console.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

import console.mapper.WalletManagerNewMapper;
import console.model.entity.WalletManagerNew;

@Service
public class WalletManagerNewService extends ServiceImpl<WalletManagerNewMapper, WalletManagerNew> {

    public List<WalletManagerNew> getAllWalletManagers() {
        LambdaQueryWrapper<WalletManagerNew> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletManagerNew::getMerchantCore, "tcgdemov3");
        return list(queryWrapper);
    }

}
