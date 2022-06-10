package kr.co.skh.agent.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Getter @Builder(toBuilder = true) @ToString
@NoArgsConstructor @AllArgsConstructor
@Component @Scope("prototype")
public class HelmetLocation {
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;
    private String helmetNo;
}
