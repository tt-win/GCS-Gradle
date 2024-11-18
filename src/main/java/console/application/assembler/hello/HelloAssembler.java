package console.application.assembler.hello;

import org.springframework.stereotype.Component;

import console.domain.hello.entity.Greeting;
import console.application.dto.response.HelloResponse;

@Component
public class HelloAssembler {
    public HelloResponse toResponse(Greeting greeting) {
        HelloResponse response = new HelloResponse();
        response.setMessage(greeting.getMessage());
        return response;
    }
}
