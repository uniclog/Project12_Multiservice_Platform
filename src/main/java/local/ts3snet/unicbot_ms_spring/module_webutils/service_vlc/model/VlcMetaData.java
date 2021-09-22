package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

/**
 * Vlc Metadata
 * parse from http web server
 */
@Data
@Component
public class VlcMetaData {
    private String title;
    private String genre;
    private String nowPlaying;

    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private WebServerVlcXmlParser webServerVlcXmlParser;
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private JsoupOptions options;

    public VlcMetaData(WebServerVlcXmlParser webServerVlcXmlParser,
                       JsoupOptions options) {
        this.webServerVlcXmlParser = webServerVlcXmlParser;
        this.options = options;

    }

    @PostConstruct
    private void init() {
        options.setUrl("http://127.0.0.1:8080/requests/status.xml");
        options.addHeader("Content-Type", "application/x-www-form-urlencoded");
        options.addHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(":1".getBytes()));
        this.updateAll();
    }

    /**
     * Update all fields, request to vlc http server
     */
    public void updateAll() {
        title = webServerVlcXmlParser.xmlParserByAttributeValue(options,"name", "title");
        genre = webServerVlcXmlParser.xmlParserByAttributeValue(options,"name", "genre");
        nowPlaying = webServerVlcXmlParser.xmlParserByAttributeValue(options,"name", "now_playing");
    }
}
