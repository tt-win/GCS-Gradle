package console.application.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletManagerRequest {
    private String merchantCode;
}
