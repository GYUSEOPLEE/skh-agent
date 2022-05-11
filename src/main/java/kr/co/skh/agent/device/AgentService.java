package kr.co.skh.agent.device;

import kr.co.skh.agent.communication.HelmetLocation;
import kr.co.skh.agent.communication.HelmetWear;

public interface AgentService {
    public HelmetWear checkHelmetWear();
    public void warnHelmetNoWear();
    public HelmetLocation checkHelmetLocation();
    public void warnHelmetLoss();
}
