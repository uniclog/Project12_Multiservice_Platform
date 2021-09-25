package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.impl;

import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.model.HttpOptions;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_http.service.WebUtilsHttpExecutorService;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.model.VlcMetaData;
import local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service.VlcControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

@Slf4j
@Service
public class VlcControlServiceImpl implements VlcControlService {

    private final HttpOptions httpOptions;
    private final WebUtilsHttpExecutorService httpExecutorService;
    private final VlcMetaData vlc;

    public VlcControlServiceImpl(HttpOptions httpOptions,
                                 WebUtilsHttpExecutorService httpExecutorService,
                                 VlcMetaData vlc) {
        this.httpOptions = httpOptions;
        this.httpExecutorService = httpExecutorService;
        this.vlc = vlc;
    }

    @PostConstruct
    public void init() {
        httpOptions.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpOptions.addHeader("Authorization", "Basic " + DatatypeConverter.printBase64Binary(":1".getBytes()));
    }

    @Override
    public String getTrackName() {
        vlc.updateAll();
        return vlc.getNowPlaying();
    }

    @Override
    public String getStationName() {
        vlc.updateAll();
        return vlc.getTitle();
    }

    @Override
    public void nextStation() {
        httpOptions.setUrl("http://127.0.0.1:8080/requests/status.xml?command=pl_next");
        httpExecutorService.sentRequestHttpMethod(httpOptions);
    }

    @Override
    public void prevStation() {
        httpOptions.setUrl("http://127.0.0.1:8080/requests/status.xml?command=pl_previous");
        httpExecutorService.sentRequestHttpMethod(httpOptions);
    }

    @Override
    public void playStationById(String id) {
        httpOptions.setUrl("http://127.0.0.1:8080/requests/status.xml?command=pl_play&id=" + id);
        httpExecutorService.sentRequestHttpMethod(httpOptions);
    }
}
