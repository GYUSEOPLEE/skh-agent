package kr.co.skh.agent.communication;

import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.util.CommunicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunicationServiceImpl implements CommunicationService{
    @Autowired
    CommunicationUtil communicationUtilimpl;
    @Autowired
    Helmet helmet;

    @Override
    public void sendHelmet() throws Exception {
        System.out.println(helmet);
        communicationUtilimpl.request(helmet);
    }

    @Override
    public void sendHelmetWear() {
    }

    @Override
    public void sendHelmetLocation() {
    }
}
