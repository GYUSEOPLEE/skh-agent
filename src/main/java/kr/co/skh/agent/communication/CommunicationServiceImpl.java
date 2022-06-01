package kr.co.skh.agent.communication;

import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.util.CommunicationUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@EnableScheduling
@Service
public class CommunicationServiceImpl extends Thread implements CommunicationService{
    @Autowired CommunicationUtil communicationUtil;
    @Autowired AgentService agentService;
    @Autowired Kickboard kickboard;
    @Autowired HelmetLocation helmetLocation;
    @Autowired Helmet helmet;
    @Value("${path}") String watchPath;

    // 헬멧 정보 전송
    @Override
    public void sendHelmet() throws IOException, JSONException {
        boolean result = communicationUtil.request(helmet); //true / false 후 처리
    }

    // 헬멧 착용 여부 송신 스레드 처리
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            sendHelmetWear();
        }
    }

    // 헬멧 착용 여부 송신
    @Override
    public void sendHelmetWear() {
        if ("Y".equals(kickboard.getUse())) {
            try {
                HelmetWear helmetWear = agentService.checkHelmetWear();
                // 헬멧 미착용시 경고음 알림
                if ("N".equals(helmetWear.getWear())) {
                    agentService.warnHelmetNoWear();
                }
                boolean result = communicationUtil.request(helmetWear);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean result = communicationUtil.request(HelmetWear.builder()
                        .helmetNo(helmet.getNo())
                        .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 헬멧 위치 정보 송신
    @Override
    @Scheduled(initialDelay = 1000, cron = "0/5 * * * * ?")
    public void sendHelmetLocation() {
        //  현재시간 측정
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
        try {
            // 헬멧 위치 정보를 읽어와 HelmetLocation 객체 생성 및 dateTime 할당
            helmetLocation = agentService.checkHelmetLocation().toBuilder()
                    .dateTime(localDateTime)
                    .build();
            // 생성된 HelmetLocation 객체 정보 전송
            boolean result = communicationUtil.request(helmetLocation);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
