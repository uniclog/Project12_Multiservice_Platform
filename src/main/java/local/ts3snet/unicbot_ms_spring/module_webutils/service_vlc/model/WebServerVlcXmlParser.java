package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.model;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Parser Vlc metadata
 */
@Slf4j
@Component
public class WebServerVlcXmlParser {
    /**
     * Parse xml from url
     * @param options base jsoup options
     * @param key attribute name
     * @param value attribute value
     * @return parse text
     */
    public String xmlParserByAttributeValue(JsoupOptions options, String key, String value) {
        Document doc;
        try {
            Connection connection = Jsoup.connect(options.getUrl());
            connection.postDataCharset(options.getDataCharset());
            options.getHeaders()
                    .forEach(connection::header);
            doc = connection.get();

            String text = doc.getElementsByAttributeValue(key, value).text();
            log.debug(text);
            return text;
        } catch (IOException e) {
            log.error(e.getMessage());
            return "null";
        }
    }
}
