package am.profclub.notification.integration;

import am.profclub.notification.config.mongo.MongoConfig;
import am.profclub.notification.config.rabbit.RabbitConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@IntegrationComponentScan
@EnableAutoConfiguration
@Import({EchoFlowOutBound.class, EchoFlowInbound.class, MongoConfig.class, RabbitConfig.class})
public class InfrastructureConfiguration {

    /*@Bean(name = "store-channel")
    @Description("Stores non filtered messages to the mongo database")
    public MessageChannel storeChannel() {
        return new DirectChannel();
    }*/
}
