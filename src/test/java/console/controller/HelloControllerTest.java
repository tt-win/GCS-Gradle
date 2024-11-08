package console.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import console.model.entity.WalletManager;
import console.model.entity.WalletManagerNew;
import console.service.HelloService;
import console.service.WalletManagerNewService;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    @Mock
    private HelloService helloService;

    @Mock
    private WalletManagerNewService walletManagerNewService;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void testHello() throws Exception {
        // 準備測試數據
        List<WalletManager> walletManagers = new ArrayList<>();
        walletManagers.add(new WalletManager());
        walletManagers.add(new WalletManager());

        // 設置模擬對象的行為
        when(helloService.getAllWalletManagers()).thenReturn(walletManagers);

        // 執行請求並驗證結果
        mockMvc.perform(get("/hello1"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World1!"));

        // 驗證模擬對象的方法是否被調用
        verify(helloService, times(1)).getAllWalletManagers();
    }

    @Test
    public void testHello2() throws Exception {
        List<WalletManager> walletManagers = new ArrayList<>();
        walletManagers.add(new WalletManager());
        walletManagers.add(new WalletManager());

        when(helloService.getAllWalletManagersByMybatis()).thenReturn(walletManagers);

        mockMvc.perform(get("/hello2"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World2!"));

        verify(helloService, times(1)).getAllWalletManagersByMybatis();
    }

    @Test
    public void testHello3() throws Exception {
        List<WalletManager> walletManagers = new ArrayList<>();
        walletManagers.add(new WalletManager());
        walletManagers.add(new WalletManager());
        walletManagers.add(new WalletManager());

        when(helloService.getWalletManagerList()).thenReturn(walletManagers);

        mockMvc.perform(get("/hello3"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World3!"));

        verify(helloService, times(1)).getWalletManagerList();
    }

    @Test
    public void testHello4() throws Exception {
        List<WalletManagerNew> walletManagerNews = new ArrayList<>();
        walletManagerNews.add(new WalletManagerNew());
        walletManagerNews.add(new WalletManagerNew());
        walletManagerNews.add(new WalletManagerNew());
        walletManagerNews.add(new WalletManagerNew());

        when(walletManagerNewService.getAllWalletManagers()).thenReturn(walletManagerNews);

        mockMvc.perform(get("/hello4"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello World4!"));

        verify(walletManagerNewService, times(1)).getAllWalletManagers();
    }

}
