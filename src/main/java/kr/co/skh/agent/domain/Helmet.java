package kr.co.skh.agent.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ConfigurationProperties("helmet")
@Component
public class Helmet implements Serializable {
    private String no;
    private String model;
    private String ip;
    private String kickboardIp;
    private String activation;
    private String size;
}
