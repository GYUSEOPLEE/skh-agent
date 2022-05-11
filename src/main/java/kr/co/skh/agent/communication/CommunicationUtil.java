package kr.co.skh.agent.communication;

public interface CommunicationUtil {
    public boolean request(Helmet helmet);
    public boolean request(HelmetWear helmetWear);
    public boolean request(HelmetLocation helmetLocation);
}
