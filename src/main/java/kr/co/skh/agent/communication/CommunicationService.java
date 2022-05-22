package kr.co.skh.agent.communication;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CommunicationService {
    public void sendHelmet() throws Exception;
    public void sendHelmetWear();
    public void sendHelmetLocation();

}
