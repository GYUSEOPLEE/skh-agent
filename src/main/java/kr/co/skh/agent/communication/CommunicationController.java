package kr.co.skh.agent.communication;

import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.exception.ReceiveState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
public class CommunicationController {
    @Autowired CommunicationService communicationService;
    @Autowired AgentService agentService;

    //킥보드 사용 여부 수신
    @PostMapping("kickboard/use")
    public ResponseEntity<ReceiveState> receiveKickboardUse(@RequestBody @Valid Kickboard kickboard) {
        // 사용 정보 수신이랑 헬멧 착용 여부 확인은 관련이 없다.
        // 단순히 정보를 받아서 객체에 정보만 담아주면 된다.
        // 톰캣과 동시에 착용 여부를 주기적으로 체크하며, 사용 정보가 Y일때만 초음파 센서를 작동 시킨다.
        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }

    //헬멧 분실 여부 수신
    @PostMapping("/helmet/loss")
    public ResponseEntity<ReceiveState> receiveHelmetLoss(@RequestBody @Valid @NotBlank String loss) {
        // 헬멧 분실 여부 수신 후, 분실일시 헬멧 분실 경고음 발생
        if ("Y".equals(loss)) {
            agentService.warnHelmetLoss();
        }
        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }
}
