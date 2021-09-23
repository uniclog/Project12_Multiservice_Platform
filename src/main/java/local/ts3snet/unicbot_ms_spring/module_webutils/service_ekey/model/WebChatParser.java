package local.ts3snet.unicbot_ms_spring.module_webutils.service_ekey.model;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Web Parsers
 */
@Slf4j
@Component
public class WebChatParser {
    /**
     * Keys parser
     * @param msg Massage
     * @param pattern Key pattern
     * @return list keys
     */
    public List<String> parseKey(String msg, String pattern) {
        List<String> allMatches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(msg);
        while (matcher.find()) {
            if(matcher.group().equals("")) continue;
            allMatches.add(matcher.group());
        }
        return allMatches;
    }

    /**
     * Message parser use Jsoup lib
     * @param url web address
     * @return list chat nodes
     */
    public List<WebChatNode> messageParser(String url) {
        List<WebChatNode> nodes = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("tr");
            for (Element e : elements) {
                WebChatNode node = new WebChatNode();
                if(e.id().equals("-1")) continue;
                String id = e.id();//id
                String time = e.getElementsByTag("div").text();//time
                String name = e.getElementsByTag("b").text();//name
                String text = e.text();
                String msg = text.substring( text.indexOf(":")+3 );//message
                node.setId(Long.parseLong(id));
                node.setTime(time);
                node.setAuthor(name);
                node.setMessage(msg);

                nodes.add(0, node);
            }
        } catch (NumberFormatException | IOException e) {
            log.error(e.getMessage());
        }
        return nodes;
    }
}
