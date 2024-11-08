package console.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import console.mapper.WalletManagerMapper;
import console.model.entity.WalletManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private WalletManagerMapper walletManagerMapper;

    private HelloService helloService;

    private List<WalletManager> mockWalletManagers;

    @BeforeEach
    void setUp() {
        // 初始化 Service
        helloService = new HelloService(jdbcTemplate, walletManagerMapper);

        // 準備測試數據
        mockWalletManagers = Arrays.asList(
                createWalletManager((long) 18675, (long) 3870, "tcgdemov3"),
                createWalletManager((long) 474, (long) 1405002, "testb2c1")
                                          );
    }

    @Test
    void getAllWalletManagersByMybatis_ShouldReturnWalletList() {
        // Given
        when(walletManagerMapper.getWalletManagersBySELECT())
                .thenReturn(mockWalletManagers);

        // When
        List<WalletManager> result = helloService.getAllWalletManagersByMybatis();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals((long) 18675, result.get(0).getWalletId());

        // Verify
        verify(walletManagerMapper).getWalletManagersBySELECT();
    }

    @Test
    void getWalletManagerList_ShouldReturnWalletList() {
        // Given
        when(walletManagerMapper.getWalletManagerList())
                .thenReturn(mockWalletManagers);

        // When
        List<WalletManager> result = helloService.getWalletManagerList();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals((long) 18675, result.get(0).getWalletId());

        // Verify
        verify(walletManagerMapper).getWalletManagerList();
    }

    @Test
    void getAllWalletManagers_WhenJdbcThrowsException_ShouldHandleError() {
        // Given
        String expectedSql = "SELECT wallet_id, merchant_id, merchant_core FROM T_LOBBY_WALLET_MANAGER";
        when(jdbcTemplate.query(eq(expectedSql), any(RowMapper.class)))
                .thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            helloService.getAllWalletManagers();
        });
    }

    @Test
    void getAllWalletManagers_WhenNoData_ShouldReturnEmptyList() {
        // Given
        String expectedSql = "SELECT wallet_id, merchant_id, merchant_core FROM T_LOBBY_WALLET_MANAGER";
        when(jdbcTemplate.query(eq(expectedSql), any(RowMapper.class)))
                .thenReturn(Collections.emptyList());

        // When
        List<WalletManager> result = helloService.getAllWalletManagers();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // Helper method to create test data
    private WalletManager createWalletManager(Long walletId, Long merchantId, String merchantCore) {
        WalletManager manager = new WalletManager();
        manager.setWalletId(walletId);
        manager.setMerchantId(merchantId);
        manager.setMerchantCore(merchantCore);
        return manager;
    }

    @Test
    void getAllWalletManagers_ShouldReturnWalletList() {
        // Given
        String expectedSql = "SELECT wallet_id, merchant_id, merchant_core FROM T_LOBBY_WALLET_MANAGER";
        when(jdbcTemplate.query(eq(expectedSql), any(RowMapper.class)))
                .thenAnswer(invocation -> {
                    RowMapper<WalletManager> rowMapper = invocation.getArgument(1);
                    return mockWalletManagers.stream()
                                             .map(walletManager -> {
                                                 try {
                                                     return rowMapper.mapRow(mockResultSet(walletManager), 0);
                                                 } catch (SQLException e) {
                                                     throw new RuntimeException(e);
                                                 }
                                             })
                                             .collect(Collectors.toList());
                });

        // When
        List<WalletManager> result = helloService.getAllWalletManagers();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals((long) 18675, result.get(0).getWalletId());
        assertEquals((long) 3870, result.get(0).getMerchantId());
        assertEquals("tcgdemov3", result.get(0).getMerchantCore());

        // Verify
        verify(jdbcTemplate).setFetchSize(500);
        verify(jdbcTemplate).query(eq(expectedSql), any(RowMapper.class));
    }

    // Helper method to create a mock ResultSet
    private ResultSet mockResultSet(WalletManager walletManager) throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getLong("wallet_id")).thenReturn(walletManager.getWalletId());
        when(resultSet.getLong("merchant_id")).thenReturn(walletManager.getMerchantId());
        when(resultSet.getString("merchant_core")).thenReturn(walletManager.getMerchantCore());
        return resultSet;
    }
}
