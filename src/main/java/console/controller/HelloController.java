package console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import console.model.entity.WalletManager;
import console.model.entity.WalletManagerNew;
import console.model.request.HelloRequest;
import console.service.HelloService;
import console.service.WalletManagerNewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "哈囉控制器", description = "簡單測試基本功能")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private WalletManagerNewService walletManagerNewService;

    @Autowired
    public HelloController(HelloService helloService, WalletManagerNewService walletManagerNewService) {
        this.helloService = helloService;
        this.walletManagerNewService = walletManagerNewService;
    }

    @GetMapping("/hello1")
    @Operation(summary = "JDBC測試", description = "測試JDBC效能")
    @ApiResponse(responseCode = "200", description = "Successful hello response")
    public String hello() {
        List<WalletManager> list = helloService.getAllWalletManagers();
        return "Hello World１!";
    }

    @GetMapping("/hello2")
    @Operation(summary = "MyBatis註解測試", description = "測試MyBatis註解效能")
    @ApiResponse(responseCode = "200", description = "Successful hello response")
    public String hello2() {
        System.out.println("jc test2 start...");
        List<WalletManager> list = helloService.getAllWalletManagersByMybatis();
        System.out.println("jc test2 end..."+ list.size());
        return "Hello World2!";
    }

    @GetMapping("/hello3")
    @Operation(summary = "MyBatis XML測試", description = "測試MyBatis XML效能")
    @ApiResponse(responseCode = "200", description = "Successful hello response")
    public String hello3() {
        System.out.println("hello3 start...");
        List<WalletManager> list = helloService.getWalletManagerList();
        System.out.println("hello3 end..."+ list.size());
        return "Hello World3!";
    }

    @GetMapping("/hello4")
    @Operation(summary = "MyBatis Plus測試", description = "測試MyBatis Plus效能")
    @ApiResponse(responseCode = "200", description = "Successful hello response")
    public String hello4() {
        System.out.println("hello4 start...");
        List<WalletManagerNew> list = walletManagerNewService.getAllWalletManagers();
        System.out.println("hello4 end..." + list.size());
        //return list;
        return "Hello World4!";
    }

    @GetMapping("/hello5")
    @Operation(summary = "Json Request 顯示測試", description = "測試Spring Doc效果")
    @ApiResponse(responseCode = "200", description = "Successful hello response")
    public String hello5(HelloRequest helloRequest){
        System.out.println("param1:"+helloRequest.getParam1());

        return "Hello World5!";
    }

}
