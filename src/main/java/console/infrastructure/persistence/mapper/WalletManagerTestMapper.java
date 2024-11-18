package console.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import console.infrastructure.persistence.entity.WalletManagerPO;

@Mapper
public interface WalletManagerTestMapper extends BaseMapper<WalletManagerPO> {
}
