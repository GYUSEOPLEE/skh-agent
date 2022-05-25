package kr.co.skh.agent.communication;

import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.util.CommunicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommunicationServiceImpl implements CommunicationService{
    @Autowired CommunicationUtil communicationUtilimpl;
    @Autowired AgentService agentService;
    @Autowired Kickboard kickboard;
    @Autowired HelmetWear helmetWear;
    @Autowired Helmet helmet;

    // 헬멧 정보 송신
    @Override
    public void sendHelmet() throws IOException, JSONException {
        boolean result = communicationUtilimpl.request(helmet);
        //true / false 후 처리
    }

    // 헬멧 착용 여부 송신
    @Override
    public void sendHelmetWear() throws IOException, JSONException{
       // 킥보드 사용 여부 체크 while()
        while ("Y".equals(kickboard.getUse())) {
//            //TODO 초음파 센서 작동 (헬멧 착용 여부 확인)
              helmetWear = agentService.checkHelmetWear();
        }

        //TODO 헬멧 미착용 시 경고음 발생 (헬멧 미착용 중일시)
        if ("N".equals(helmetWear.getWear())) {
            agentService.warnHelmetNoWear();
        }

        //헬멧 착용 여부를 송신한다.
        boolean result = communicationUtilimpl.request(helmetWear);
        // true / false 후 처리
    }

    //TODO 헬멧 위치 정보 송신
    @Override
    public void sendHelmetLocation() throws IOException, JSONException {
        // TODO 현재 시간 체크
        // 헬멧 위치 정보 확인
        HelmetLocation helmetLocation = agentService.checkHelmetLocation();
        // 위치 정보 송신
        boolean result = communicationUtilimpl.request(helmetLocation);
    }
}
