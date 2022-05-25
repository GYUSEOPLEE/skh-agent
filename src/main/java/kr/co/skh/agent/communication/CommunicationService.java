package kr.co.skh.agent.communication;


import kr.co.skh.agent.domain.Kickboard;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;

public interface CommunicationService {
    public void sendHelmet() throws IOException, JSONException;
    public void sendHelmetWear() throws IOException, JSONException;
    public void sendHelmetLocation() throws IOException, JSONException;

}
