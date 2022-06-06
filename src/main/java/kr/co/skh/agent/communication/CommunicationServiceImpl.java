package kr.co.skh.agent.communication;

import kr.co.skh.agent.config.ApplicationContextProvider;
import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.util.CommunicationUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Log4j2
@EnableScheduling
@Service
public class CommunicationServiceImpl extends Thread implements CommunicationService{
    @Autowired private CommunicationUtil communicationUtil;
    @Autowired private AgentService agentService;
    @Autowired private Kickboard kickboard;
    @Autowired private HelmetLocation helmetLocation;
    @Autowired private Helmet helmet;
    @Value("${path}") String watchPath;

    public CommunicationServiceImpl(Kickboard kickboard) {
        this.kickboard = kickboard;
    }
    // 헬멧 정보 전송
    @Override
    public void sendHelmet()  {
        try {
            boolean result = communicationUtil.request(helmet); //true / false 후 처리
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    // 헬멧 착용 여부 송신 스레드 처리
    @Override
    public void run() {
        while (true) {
            log.info("스레드 실행");
            try {
                sendHelmetWear();
                Thread.sleep(2000);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }

    // 헬멧 착용 여부 송신
    @Override
    @Async(value = "applicationTaskExecutor")
    public void sendHelmetWear() {
        AgentService agentService = ApplicationContextProvider.getBean(AgentService.class);
        CommunicationUtil communicationUtil = ApplicationContextProvider.getBean(CommunicationUtil.class);
        log.info("킥보드 사용여부 " + kickboard.getUse());
        if ("Y".equals(kickboard.getUse())) {
            try {
                log.info("checkHelmetWear 호출 전");
                HelmetWear helmetWear = agentService.checkHelmetWear();
                log.info("헬멧 착용 여부 체크 " + helmetWear.toString());
                // 헬멧 미착용시 경고음 알림
                if ("N".equals(helmetWear.getWear())) {
                    agentService.warnHelmetNoWear();
                    log.info("미착용 경고음 성공 ");
                }
                boolean result = communicationUtil.request(helmetWear);
                log.info("헬멧 착용 여부 송신 성공 (킥보드 사용중) " + result);
            } catch (Exception e) {
                log.info(String.valueOf(e));
            }
        }
        else {
            try {
                log.info("헬멧 착용 여부 request() 전");
                Helmet helmet = ApplicationContextProvider.getBean(Helmet.class);
                boolean result = communicationUtil.request(HelmetWear.builder()
                        .helmetNo(helmet.getNo())
                        .build());
                log.info("헬멧 착용 여부 송신 성공 (킥보드 미사용중) " + result);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }

    // 헬멧 위치 정보 송신
    @Override
    @Scheduled(cron = "0/5 * * * * ?")
    public void sendHelmetLocation() {
        //  현재시간 측정
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        try {
            // 헬멧 위치 정보를 읽어와 HelmetLocation 객체 생성 및 dateTime 할당
            helmetLocation = agentService.checkHelmetLocation().toBuilder()
                    .dateTime(localDateTime)
                    .build();
            log.info("헬멧 위치 정보 체크 " + helmetLocation.toString());
            // 생성된 HelmetLocation 객체 정보 전송
            CommunicationUtil communicationUtil = ApplicationContextProvider.getBean(CommunicationUtil.class);
            boolean result = communicationUtil.request(helmetLocation);
            log.info("헬멧 위치 정보 송신 성공 " + result);
        } catch(Exception e) {
            log.info(e.getMessage());
        }
    }
}
