package console.domain.service.wallet;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import console.domain.wallet.service.WalletManagerNewService;
import console.infrastructure.persistence.mapper.WalletManagerNewMapper;
import console.infrastructure.persistence.entity.WalletManagerNew;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletManagerNewServiceTest {

    @Mock
    private WalletManagerNewMapper walletManagerNewMapper;

    @InjectMocks
    private WalletManagerNewService walletManagerNewService;

    private List<WalletManagerNew> mockWalletManagers;

    @BeforeEach
    void setUp() {
        mockWalletManagers = Arrays.asList(
                createWalletManager(1L, "tcgdemov3"),
                createWalletManager(2L, "tcgdemov3")
                                          );

        // 設置返回值
        when(walletManagerNewMapper.selectList(any())).thenReturn(mockWalletManagers);
    }

    @Test
    void getAllWalletManagers_ShouldReturnFilteredList() {
        // When
        List<WalletManagerNew> result = walletManagerNewService.getAllWalletManagers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        result.forEach(manager ->
                               assertEquals("tcgdemov3", manager.getMerchantCore())
                      );

        // 驗證 selectList 被調用
        verify(walletManagerNewMapper).selectList(any());
    }

    @Test
    void getAllWalletManagers_WhenNoData_ShouldReturnEmptyList() {
        // Given
        when(walletManagerNewMapper.selectList(any())).thenReturn(Collections.emptyList());

        // When
        List<WalletManagerNew> result = walletManagerNewService.getAllWalletManagers();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(walletManagerNewMapper).selectList(any());
    }

    @Test
    void getAllWalletManagers_ShouldVerifyServiceCall() {
        // When
        walletManagerNewService.getAllWalletManagers();

        // Then
        // 只驗證方法被調用
        verify(walletManagerNewMapper).selectList(any(LambdaQueryWrapper.class));

        // 驗證調用次數
        verify(walletManagerNewMapper, times(1)).selectList(any());
    }

    private WalletManagerNew createWalletManager(Long id, String merchantCore) {
        WalletManagerNew manager = new WalletManagerNew();
        manager.setWalletId(id);
        manager.setMerchantCore(merchantCore);
        return manager;
    }
}
