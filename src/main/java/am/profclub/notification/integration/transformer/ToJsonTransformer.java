package am.profclub.notification.integration.transformer;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.springframework.core.convert.converter.Converter;

public class ToJsonTransformer implements Converter<String, String> {

    @Override
    public String convert(String str) {
        DBObject dbObj = new BasicDBObject();
        dbObj.put("val", str);
        String json = JSON.serialize( dbObj );
        return json;
    }
}
