package kr.co.skh.agent.device;

import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;

public interface AgentService {
    public HelmetWear checkHelmetWear();
    public HelmetLocation checkHelmetLocation();
    public void warnHelmetNoWear();
    public void warnHelmetLoss();
}
