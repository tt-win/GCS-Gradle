package console.domain.hello.entity;

public class Greeting {
    private String message;

    public Greeting(String message) {
        validateMessage(message);
        this.message = message;
    }

    private void validateMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("message is null");
        }
    }

    public String getMessage() {
        return message;
    }
}
