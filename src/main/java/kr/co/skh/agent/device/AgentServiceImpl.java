package kr.co.skh.agent.device;
import kr.co.skh.agent.domain.Helmet;
import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.StringTokenizer;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired private Helmet helmet;
    @Value("${directory-path}") private String directoryPath;
    @Value("${helmetWear-filePath}") private String wearPath;
    @Value("${helmetLocation-filePath") private String locationPath;

    //TODO 헬멧 착용 여부 확인
    @Override
    public HelmetWear checkHelmetWear() throws Exception {
        // 와치 서비스 구현
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(directoryPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey watchKey = watchService.take();
        watchKey.pollEvents();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(directoryPath + wearPath));
        if (Integer.parseInt(bufferedReader.readLine().trim()) < 50) {
            return HelmetWear.builder()
                    .helmetNo(helmet.getNo())
                    .wear("Y")
                    .build();
        }
        return HelmetWear.builder()
                .helmetNo(helmet.getNo())
                .wear("N")
                .build();
    }

    //TODO 헬멧 위치 확인 (GPS모듈)
    @Override
    public HelmetLocation checkHelmetLocation() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(directoryPath + locationPath));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().replaceAll("\\p{Z}",""), ",");
        return HelmetLocation.builder()
                .latitude(Double.parseDouble(stringTokenizer.nextToken()))
                .longitude(Double.parseDouble(stringTokenizer.nextToken()))
                .helmetNo(helmet.getNo())
                .build();
    }

    //TODO 헬멧 미착용시 경고음 (부저)
    @Override
    public void warnHelmetNoWear() {

    }

    //TODO 헬멧 분실 시 경고음 (부저)
    @Override
    public void warnHelmetLoss() {

    }
}
