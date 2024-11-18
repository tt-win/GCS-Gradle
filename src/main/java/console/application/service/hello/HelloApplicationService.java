package console.application.service.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import console.application.assembler.hello.HelloAssembler;
import console.domain.hello.entity.Greeting;
import console.domain.hello.service.HelloDomainService;
import console.application.dto.response.HelloResponse;

@Service
public class HelloApplicationService {
    private final HelloDomainService helloDomainService;
    private final HelloAssembler helloAssembler;  // DTO 轉換

    @Autowired
    public HelloApplicationService(HelloDomainService helloDomainService, HelloAssembler helloAssembler) {
        this.helloDomainService = helloDomainService;
        this.helloAssembler = helloAssembler;
    }

    public HelloResponse getHelloMessage() {
        Greeting greeting = helloDomainService.createGreeting();
        return helloAssembler.toResponse(greeting);
    }
}