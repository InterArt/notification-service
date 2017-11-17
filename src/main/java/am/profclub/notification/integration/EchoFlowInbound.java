package am.profclub.notification.integration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.amqp.AmqpInboundGatewaySpec;

@Configuration
public class EchoFlowInbound {

	@Autowired
	private RabbitConfig rabbitConfig;

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public IntegrationFlow inboundFlow() {
		AmqpInboundGatewaySpec spec = Amqp.inboundGateway(connectionFactory, rabbitConfig.transactionQueue());
		spec.defaultReplyTo("");
		return IntegrationFlows.from(spec)
				.get();
	}

}
