package console.application.service.wallet;

import org.springframework.stereotype.Service;

import java.util.List;

import console.application.assembler.wallet.WalletManagerAssembler;
import console.application.dto.response.WalletManagerResponse;
import console.domain.hello.entity.WalletManager;
import console.domain.wallet.repository.WalletManagerRepository;
import console.infrastructure.storage.annotation.DataSourceStrategy;
import console.infrastructure.storage.enums.DataSourceType;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletManagerApplicationService {
    private final WalletManagerRepository walletManagerRepository;
    private final WalletManagerAssembler assembler;

    @DataSourceStrategy(
            key = "'wallet:merchant:' + #merchantCode",
            order = {DataSourceType.CAFFEINE, DataSourceType.REDIS, DataSourceType.ORACLE})
    public List<WalletManagerResponse> getAllWalletManagers() {
        List<WalletManager> walletManagers =
                walletManagerRepository.findByMerchantCode("tcgdemov3");
        return assembler.toResponseList(walletManagers);
    }
}
