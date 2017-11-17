package am.profclub.notification.integration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.NullChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RabbitConfig {

	@Autowired
	private ConnectionFactory rabbitConnectionFactory;

	@Bean
    DirectExchange rubeExchange() {
		return new DirectExchange("pei.transaction.exchange", true, false);
	}

	@Bean
	public Queue transactionQueue() {
		return new Queue("pei.transaction.queue", true);
	}

	@Bean
	public MessageChannel replyToChannel() {
		return new NullChannel();
	}

	@Bean
    Binding rubeExchangeBinding(DirectExchange rubeExchange, Queue rubeQueue) {
		return BindingBuilder.bind(rubeQueue).to(rubeExchange).with("pei.key");
	}

	@Bean
	public RabbitTemplate transactionExchangeTemplate() {
		RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
		r.setExchange("pei.transaction.exchange");
		r.setRoutingKey("pei.key");
		r.setConnectionFactory(rabbitConnectionFactory);
		return r;
	}

	@Bean
	public RabbitMessagingTemplate rabbitMessagingTemplate() {
		return new RabbitMessagingTemplate(transactionExchangeTemplate());
	}
}
