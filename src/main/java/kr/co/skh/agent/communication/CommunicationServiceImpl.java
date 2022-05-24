package kr.co.skh.agent.communication;

import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.util.CommunicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImpl implements CommunicationService{
    @Autowired CommunicationUtil communicationUtilimpl;
    @Autowired AgentService agentService;
    @Autowired Helmet helmet;

    @Override
    public void sendHelmet() throws Exception {
        boolean flag = communicationUtilimpl.request(helmet);
        //true / false 후 처리
    }

    @Override
    public void sendHelmetWear(Kickboard kickboard) {
//        // 킥보드 사용 여부 체크 while()
//        while ("Y".equals(kickboard.getUse())) {
//            // 초음파 센서 작동 (헬멧 착용 여부 확인)
//
//            // 헬멧 미착용 시 경고음 발생
//            if ("N".equals(helmetWear.getWear())) {
//                //부저 코드
//                warnHelmetNoWear();
//            }
//            return HelmetWear.builder()
//                    .no(helmet.getNo())
//                    .wear("Y")
//                    .build();
//        }
        // 헬멧 착용 여부를 확인한다.
        HelmetWear helmetWear = agentService.checkHelmetWear();
        // 착용 여부가 N일 경우 헬멧 미착용 알림을 한다.
        if ("N".equals(helmetWear.getWear())) {
            agentService.warnHelmetNoWear();
        }
        //헬멧 착용 여부를 송신한다.
        boolean flag = communicationUtilimpl.request(helmetWear);
        // true / false 후 처리
    }

    //TODO 헬멧 위치 전송
    @Override
    public void sendHelmetLocation() {
    }
}
