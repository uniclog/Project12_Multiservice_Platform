package local.ts3snet.unicbot_ms_spring.module_webutils.service_vlc.service;

public interface VlcControlService {
     String getTrackName();
     String getStationName();

     void nextStation();
     void prevStation();

     void playStationById(String id);
}
