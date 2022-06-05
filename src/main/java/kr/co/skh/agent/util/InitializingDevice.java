package kr.co.skh.agent.util;

import kr.co.skh.agent.communication.CommunicationService;
import kr.co.skh.agent.communication.CommunicationServiceImpl;
import kr.co.skh.agent.domain.Kickboard;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Log4j2
@Component
public class InitializingDevice {
    @Autowired private CommunicationService communicationService;
    @Autowired private Kickboard kickboard;

    @PostConstruct
    public void init() {
        communicationService.sendHelmet();
        log.debug("sendHelmet() 이후");

        //헬멧 착용정보 송신
        Thread thread = new CommunicationServiceImpl(kickboard);
        thread.start();
        log.debug("착용 정보 스레드 실행 후");

        //헬멧 위치정보(GSP)송신
        communicationService.sendHelmetLocation();
        log.debug("sendHelmetLocation() 이후");
    }

}
//@Component
//public class InitializingDevice implements InitializingBean {
//    @Autowired private CommunicationService communicationService;
//    @Autowired private Kickboard kickboard;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        //헬멧 정보 송신
//        communicationService.sendHelmet();
//        log.debug("afterPropertiesSet 헬멧 정보 송신 호출 성공");
//        System.out.println("afterPropertiesSet 헬멧 정보 송신 호출 성공");
//
//        //헬멧 착용정보 송신
//        Thread thread = new CommunicationServiceImpl(kickboard);
//        thread.start();
//        log.debug("afterPropertiesSet 헬멧 착용정보 송신 호출 스레드 성공");
//        System.out.println("afterPropertiesSet 헬멧 착용정보 송신 호출 스레드 성공");
//
//        //헬멧 위치정보(GSP)송신
//        communicationService.sendHelmetLocation();
//        log.debug("afterPropertiesSet 헬멧 위치정보 송신 호출 성공");
//        System.out.println("afterPropertiesSet 헬멧 위치정보 송신 호출 성공");
//    }
//}
