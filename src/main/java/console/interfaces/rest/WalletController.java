package console.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import console.application.dto.response.Response;
import console.application.dto.response.WalletManagerResponse;
import console.application.service.wallet.WalletManagerApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import console.domain.wallet.service.WalletService;
import console.infrastructure.persistence.entity.WalletManagerNew;

@RestController
@Tag(name = "錢包管理", description = "錢包相關功能")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletManagerApplicationService walletManagerApplicationService;

    @GetMapping("/test-all")
    @Operation(summary = "測試多數據源", description = "測試Redis->Cassandra->Oracle")
    @ApiResponse(responseCode = "200", description = "查詢成功")
    public Response<List<WalletManagerNew>> testAllSources() {
        List<WalletManagerNew> list = walletService.getAllWallets();
        return Response.success(list);
    }

    @GetMapping("/test-redis-cassandra/{merchantCode}")
    @Operation(summary = "測試多數據源", description = "測試Redis->Cassandra->Oracle")
    @ApiResponse(responseCode = "200", description = "查詢成功")
    public Response<List<WalletManagerNew>> testRedisCassandraSources(String merchantCode) {
        List<WalletManagerNew> list = walletService.getWalletsByMerchantCode(merchantCode);
        return Response.success(list);
    }

    @GetMapping("/test-caffeine-redis/{merchantCode}")
    @Operation(summary = "測試多數據源", description = "測試Caffeine->Redis->Oracle")
    @ApiResponse(responseCode = "200", description = "查詢成功")
    public Response<List<WalletManagerNew>> testCaffeineRedisOracleSources(String merchantCode) {
        List<WalletManagerNew> list = walletService.getWalletsTest(merchantCode);
        return Response.success(list);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<List<WalletManagerResponse>> getWallet(@PathVariable Long id) {
        List<WalletManagerResponse> response = walletManagerApplicationService.getAllWalletManagers();
        return ResponseEntity.ok(response);
    }

}
