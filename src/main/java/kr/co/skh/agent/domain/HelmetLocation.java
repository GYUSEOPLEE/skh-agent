package kr.co.skh.agent.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Component
public class HelmetLocation implements Serializable {
    private LocalDateTime dateTime;
    private double latitude;
    private double longtitude;
    private String kickboardNo;
}
