package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.model;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * Parser Vlc metadata
 */
@Slf4j
@Component
public class WebServerVlcXmlParser {
    /**
     * Parse xml from url
     * @param url base url
     * @param key key
     * @param value key value
     * @return parse text
     */
    public String xmlParserByAttributeValue(String url, String key, String value) {
        Document doc = null;
        try {
            doc = Jsoup
                    .connect(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + DatatypeConverter.printBase64Binary(":1".getBytes()))
                    .postDataCharset("UTF-8")
                    .get();

            String text = doc.getElementsByAttributeValue(key, value).text();
            log.debug(text);
            return text;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "null";
    }
}
