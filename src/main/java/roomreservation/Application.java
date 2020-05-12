package roomreservation;
import org.springframework.cloud.openfeign.EnableFeignClients;
import roomreservation.config.kafka.KafkaProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableFeignClients
@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
public class Application {
    protected static ApplicationContext applicationContext;
    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Application.class, args);
    }
}
