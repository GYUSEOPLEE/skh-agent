package kr.co.skh.agent.communication;

import kr.co.skh.agent.device.AgentService;
import kr.co.skh.agent.domain.Kickboard;
import kr.co.skh.agent.exception.ReceiveState;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Log4j2
@RestController
public class CommunicationController {
    @Autowired CommunicationService communicationService;
    @Autowired AgentService agentService;

    //킥보드 사용 여부 수신
    @PostMapping("/kickboard/use")
    public ResponseEntity<ReceiveState> receiveKickboardUse(@RequestBody @Valid Kickboard kickboard) {
        log.info("킥보드 사용 정보 수신 " + kickboard.getUse());

        CommunicationServiceImpl communicationServiceImpl = new CommunicationServiceImpl(kickboard);

        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }

    //헬멧 분실 여부 수신
    @PostMapping("/helmet/loss")
    public ResponseEntity<ReceiveState> receiveHelmetLoss(@RequestBody @Valid @NotBlank String loss) {
        if ("Y".equals(loss)) {
            try {
                agentService.warnHelmetLoss();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }
}