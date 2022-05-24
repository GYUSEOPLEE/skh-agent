package kr.co.skh.agent.communication;


import kr.co.skh.agent.domain.Kickboard;

public interface CommunicationService {
    public void sendHelmet() throws Exception;
    public void sendHelmetWear(Kickboard kickboard);
    public void sendHelmetLocation();

}
