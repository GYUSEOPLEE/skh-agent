package kr.co.skh.agent.communication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommunicationController {
    @RequestMapping("/kickboard/use")
    public @ResponseBody ReceiveState receiveKickboardUse(String use) {
        return null;
    }

    @RequestMapping("/helmet/loss")
    public @ResponseBody ReceiveState receiveHelmetLoss(String loss) {
        return null;
    }
}
