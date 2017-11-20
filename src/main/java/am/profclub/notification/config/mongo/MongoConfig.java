package am.profclub.notification.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.messaging.MessageHandler;

import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoCredential userCredentials =
                MongoCredential.createCredential("peiMongoUsr", "peimdb", "peiMongo17Pass".toCharArray());

        MongoClient client = new MongoClient(new ServerAddress("192.168.99.100", 27017),
                Arrays.asList(userCredentials));

        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(client, "peimdb");
        return mongoDbFactory;
    }

    @Bean
    @ServiceActivator(inputChannel = "storeChannel")
    public MessageHandler mongoOutboundAdapter() throws Exception {
        MongoDbStoringMessageHandler mongoHandler = new MongoDbStoringMessageHandler(mongoDbFactory());
        mongoHandler.setCollectionNameExpression(new LiteralExpression("echo"));
        return mongoHandler;
    }
}
