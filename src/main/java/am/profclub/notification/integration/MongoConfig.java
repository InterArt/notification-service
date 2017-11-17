package am.profclub.notification.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.messaging.MessageHandler;

@ComponentScan("test.integration")
@IntegrationComponentScan("test.integration")
public class MongoConfig {

    @Bean
    @Autowired
    public MessageHandler mongoOutboundAdapter(MongoDbFactory mongo) {
        MongoDbStoringMessageHandler mongoHandler = new MongoDbStoringMessageHandler(mongo);
        mongoHandler.setCollectionNameExpression(new LiteralExpression("product"));
        return mongoHandler;
    }
/*
    @Bean
    @ServiceActivator(inputChannel = "personInput")
    public MessageHandler mongodbOutbound(MongoDbFactory mongo) {
        MongoDbExecutor mongoDbExecutor = new MongoDbExecutor(mongo);
        mongoDbExecutor.setCollectionNameExpression(new LiteralExpression("person"));
        mongoDbExecutor.setMongoDbQueryExpression("payload.data");
        mongoDbExecutor.setExpectSingleResult(true);

        //mongoDbExecutor.setEntityClass(Person.class);

        MongoDbOutboundGateway gateway = new MongoDbOutboundGateway(mongoDbExecutor);
        gateway.setOutputChannelName("personOutput");

        return gateway;
    }*/
}
