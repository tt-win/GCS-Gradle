package console.application.assembler.wallet;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import console.application.dto.request.WalletManagerRequest;
import console.application.dto.response.WalletManagerResponse;
import console.domain.hello.entity.WalletManager;

@Component
public class WalletManagerAssembler {

    // Domain -> DTO (Response)
    public WalletManagerResponse toResponse(WalletManager domain) {
        return WalletManagerResponse.builder()
                                    .walletId(domain.getWalletId())
                                    .merchantCode(domain.getMerchantCode())
                                    .build();
    }

    // List<Domain> -> List<DTO>
    public List<WalletManagerResponse> toResponseList(List<WalletManager> domains) {
        return domains.stream()
                      .map(this::toResponse)
                      .collect(Collectors.toList());
    }

    // DTO (Request) -> Domain
    public WalletManager toDomain(WalletManagerRequest request) {
        return new WalletManager(
                null,  // 新建時 ID 為空
                request.getMerchantCode()
        );
    }
}
