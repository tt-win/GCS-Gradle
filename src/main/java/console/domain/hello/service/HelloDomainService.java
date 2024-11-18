package console.domain.hello.service;

import org.springframework.stereotype.Service;

import console.domain.hello.entity.Greeting;

@Service
public class HelloDomainService {
    public Greeting createGreeting() {
        return new Greeting("Hello World");
    }
}
