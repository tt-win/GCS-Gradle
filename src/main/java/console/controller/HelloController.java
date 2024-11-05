package console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import console.model.WalletManager;
import console.service.HelloService;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("jc test start...");
        int count = helloService.getAllWalletManagers();
        System.out.println("jc test end..."+ count);
        return "Hello World!";
    }

    @GetMapping("/hello2")
    public String hello2() {
        System.out.println("jc test2 start...");
        int count = helloService.getAllWalletManagersByMybatis();
        System.out.println("jc test2 end..."+ count);
        return "Hello World2!";
    }

    @GetMapping("/hello3")
    public String hello3() {
        System.out.println("hello3 start...");
        helloService.getWalletManagerList();
        System.out.println("hello3 end...");
        return "Hello World3!";
    }

}
