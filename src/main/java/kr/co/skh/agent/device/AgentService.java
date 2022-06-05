package kr.co.skh.agent.device;

import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;

public interface AgentService {
    public HelmetWear checkHelmetWear() throws Exception;
    public HelmetLocation checkHelmetLocation() throws Exception;
    public void warnHelmetNoWear() throws InterruptedException;
    public void warnHelmetLoss() throws InterruptedException;
}
