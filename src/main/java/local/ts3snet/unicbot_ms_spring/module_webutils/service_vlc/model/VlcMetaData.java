package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Vlc Metadata
 * parse from http web server
 */
@Data
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class VlcMetaData {
    private String title;
    private String genre;
    private String nowPlaying;

    private String url = "http://127.0.0.1:8080/requests/status.xml";

    //@Autowired
    protected WebServerVlcXmlParser webServerVlcXmlParser;

    @PostConstruct
    private void init() {
        this.updateAll();
    }

    /**
     * Update all fields, request to vlc http server
     */
    public void updateAll() {
        title = webServerVlcXmlParser.xmlParserByAttributeValue(url,"name", "title");
        genre = webServerVlcXmlParser.xmlParserByAttributeValue(url,"name", "genre");
        nowPlaying = webServerVlcXmlParser.xmlParserByAttributeValue(url,"name", "now_playing");
    }
}
