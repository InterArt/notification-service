package am.profclub.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import am.profclub.notification.integration.EchoGateway;

/**
 * Created by ARTHUR on 11/12/2017
 */
@ComponentScan(basePackages = "am.profclub.notification")
@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private EchoGateway echoGateway;

    @Override
    public void run(String... strings) throws Exception {
        //echoGateway.echo("Hello world");
    }
}
