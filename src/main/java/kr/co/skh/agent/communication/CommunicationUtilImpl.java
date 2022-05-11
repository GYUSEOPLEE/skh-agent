package kr.co.skh.agent.communication;

import org.springframework.stereotype.Service;

@Service
public class CommunicationUtilImpl implements CommunicationUtil{
    @Override
    public boolean request(Helmet helmet) {
        return false;
    }

    @Override
    public boolean request(HelmetWear helmetWear) {
        return false;
    }

    @Override
    public boolean request(HelmetLocation helmetLocation) {
        return false;
    }
}
