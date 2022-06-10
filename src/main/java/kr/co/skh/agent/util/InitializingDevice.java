package kr.co.skh.agent.util;

import kr.co.skh.agent.communication.CommunicationService;
import kr.co.skh.agent.communication.CommunicationServiceImpl;
import kr.co.skh.agent.config.ApplicationContextProvider;
import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Kickboard;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Log4j2
@Component
public class InitializingDevice {
    @Autowired private CommunicationService communicationService;
    @PostConstruct
    public void init() {
        communicationService.sendHelmet();

        Kickboard kickboard = ApplicationContextProvider.getBean(Kickboard.class);
        //헬멧 착용정보 송신
        Thread thread = new CommunicationServiceImpl(kickboard);
        thread.start();
    }
}