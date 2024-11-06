package console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import console.model.WalletManager;
import console.model.WalletManagerNew;
import console.service.HelloService;
import console.service.WalletManagerNewService;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private WalletManagerNewService walletManagerNewService;

    @GetMapping("/hello1")
    public String hello() {
        System.out.println("jc test start...");
        List<WalletManager> list = helloService.getAllWalletManagers();
        System.out.println("jc test end..."+ list.size());
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        System.out.println("jc test2 start...");
        List<WalletManager> list = helloService.getAllWalletManagersByMybatis();
        System.out.println("jc test2 end..."+ list.size());
        return "Hello World2!";
    }

    @GetMapping("/hello3")
    public String hello3() {
        System.out.println("hello3 start...");
        List<WalletManager> list = helloService.getWalletManagerList();
        System.out.println("hello3 end..."+ list.size());
        return "Hello World3!";
    }

    @GetMapping("/hello4")
    public String hello4() {
        System.out.println("hello4 start...");
        List<WalletManagerNew> list = walletManagerNewService.getAllWalletManagers();
        System.out.println("hello4 end..." + list.size());
        //return list;
        return "Hello World4!";
    }

}
