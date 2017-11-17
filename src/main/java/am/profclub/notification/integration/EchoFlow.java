package am.profclub.notification.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@IntegrationComponentScan
@EnableAutoConfiguration
@Import({EchoFlowOutBound.class, EchoFlowInbound.class, RabbitConfig.class})
public class EchoFlow {
}
