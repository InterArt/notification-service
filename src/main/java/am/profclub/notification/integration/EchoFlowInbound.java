package am.profclub.notification.integration;

import am.profclub.notification.config.rabbit.RabbitConfig;
import am.profclub.notification.integration.transformer.ToJsonTransformer;
import com.mongodb.util.JSON;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.amqp.AmqpInboundGatewaySpec;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.handler.LoggingHandler;

@Configuration
public class EchoFlowInbound {

	@Autowired
	private RabbitConfig rabbitConfig;

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public Converter<String, String> toJsonTransformer() {
		return new ToJsonTransformer();
	}

	@Bean
	public IntegrationFlow inboundFlow() {

		return IntegrationFlows.from(Amqp.inboundGateway(connectionFactory, rabbitConfig.transactionQueue()))
				.transform(Transformers.converter(toJsonTransformer()))
				.log(LoggingHandler.Level.INFO)
				.channel("storeChannel")
				.get();
	}

}
