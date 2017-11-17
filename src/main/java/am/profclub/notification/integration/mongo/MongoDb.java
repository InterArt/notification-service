package am.profclub.notification.integration.mongo;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
/**
 * Created by ARTHUR on 11/17/2017.
 */
public class MongoDb {

    public static MongoDbOutboundGatewaySpec outboundGateway(MongoDbFactory mongoDbFactory) {
        return new MongoDbOutboundGatewaySpec(new MongoDbExecutor(mongoDbFactory));
    }

    public static MongoDbOutboundGatewaySpec outboundGateway(MongoTemplate mongoTemplate) {
        return new MongoDbOutboundGatewaySpec(new MongoDbExecutor(mongoTemplate));
    }
}
