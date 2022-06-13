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
import java.util.Map;

@Log4j2
@RestController
public class CommunicationController {
    @Autowired CommunicationService communicationService;
    @Autowired AgentService agentService;

    //킥보드 사용 여부 수신
    @PostMapping("/kickboard/use")
    public ResponseEntity<ReceiveState> receiveKickboardUse(@RequestBody @Valid Kickboard kickboard) {
        CommunicationServiceImpl communicationServiceImpl = new CommunicationServiceImpl(kickboard);

        log.info("수신된 킥보드 사용 여부 {}", kickboard);
        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }

    //헬멧 분실 여부 수신
    @PostMapping("/helmet/loss")
    public ResponseEntity<ReceiveState> receiveHelmetLoss(@RequestBody @Valid @NotBlank Map<String, String> lossMap) {
        if ("Y".equals(lossMap.get("loss"))) {
            try {
                agentService.warnHelmetLoss();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        log.info("수신된 헬멧 분실 여부 {}", lossMap.get("loss"));
        return ResponseEntity.ok()
                .body(ReceiveState.builder()
                        .code("200")
                        .message("receive ok")
                        .build());
    }
}