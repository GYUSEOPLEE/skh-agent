package kr.co.skh.agent.util;

import kr.co.skh.agent.communication.CommunicationService;
import kr.co.skh.agent.communication.CommunicationServiceImpl;
import kr.co.skh.agent.device.AgentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializingDevice implements InitializingBean {
    @Autowired private CommunicationService communicationService;
    @Autowired
    private AgentService agentService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //헬멧 정보 송신
        communicationService.sendHelmet();
        //헬멧 착용정보 송신
        Thread thread = new CommunicationServiceImpl();
        thread.start();
        //헬멧 위치정보(GSP)송신
        communicationService.sendHelmetLocation();
    }
}
