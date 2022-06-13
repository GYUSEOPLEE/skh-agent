package kr.co.skh.agent.device;

import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import kr.co.skh.agent.util.SensorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

@Log4j2
@Service
@Scope("prototype")
public class AgentServiceImpl implements AgentService {
    @Autowired private Helmet helmet;
    @Value("${directory-path}") private String directoryPath;
    @Value("${helmetLocation-filePath}") private String locationPath;
    private SensorUtil sensorUtil;

    // 헬멧 착용 여부 확인
    @Override
    public HelmetWear checkHelmetWear() {
        sensorUtil = new SensorUtil();
        // 초음파 센서 실행, 물체 감지 거리가 100mm 미만시 착용으로 판단
        if (sensorUtil.detectObject() < 100) {
            return HelmetWear.builder()
                    .helmetNo(helmet.getNo())
                    .wear("Y")
                    .build();
        }
        // 물체 감지 거리가 100mm 이상시 미착용으로 판단
        return HelmetWear.builder()
                .helmetNo(helmet.getNo())
                .wear("N")
                .build();
    }

    // 헬멧 위치 확인
    @Override
    public HelmetLocation checkHelmetLocation() throws Exception {
        // GPS 모듈 위도, 경도 값 Read
        BufferedReader bufferedReader = new BufferedReader(new FileReader(directoryPath + locationPath));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().replaceAll("\\p{Z}",""), ",");

        return HelmetLocation.builder()
                .latitude(Double.parseDouble(stringTokenizer.nextToken()))
                .longitude(Double.parseDouble(stringTokenizer.nextToken()))
                .helmetNo(helmet.getNo())
                .build();
    }

    // 헬멧 미착용 시 경고음
    @Override
    public void warnHelmetNoWear() throws InterruptedException {
        sensorUtil = new SensorUtil();
        sensorUtil.buzzer();
        log.info("헬멧 미착용 경고 부저실행 완료");
    }

    // 헬멧 분실 시 경고음
    @Override
    public void warnHelmetLoss() throws InterruptedException {
        sensorUtil = new SensorUtil();
        sensorUtil.buzzer();
        log.info("헬멧 분실 경고 부저실행 완료");
    }

}
