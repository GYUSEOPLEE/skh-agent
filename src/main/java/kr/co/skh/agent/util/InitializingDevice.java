package kr.co.skh.agent.util;

import kr.co.skh.agent.communication.CommunicationService;
import kr.co.skh.agent.communication.CommunicationServiceImpl;
import kr.co.skh.agent.config.ApplicationContextProvider;
import kr.co.skh.agent.domain.Kickboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import java.io.*;

@Log4j2
@Component
public class InitializingDevice {
    @Autowired private CommunicationService communicationService;
    @Value("${pathInfo}") String pathInfo;

    @PostConstruct
    public void init() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(pathInfo)));
        CommunicationUtil.info.put("systemIp", bufferedReader.readLine().trim().split("=")[1]);

        communicationService.sendHelmet();

        Kickboard kickboard = ApplicationContextProvider.getBean(Kickboard.class);
        //헬멧 착용정보 송신
        Thread thread = new CommunicationServiceImpl(kickboard);
        thread.start();
    }
}