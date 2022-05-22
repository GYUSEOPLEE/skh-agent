package kr.co.skh.agent.util;

import kr.co.skh.agent.communication.CommunicationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializingDevice implements InitializingBean {
    @Autowired private CommunicationService communicationService;

    @Override
    public void afterPropertiesSet() throws Exception {
        communicationService.sendHelmet();
    }
}
