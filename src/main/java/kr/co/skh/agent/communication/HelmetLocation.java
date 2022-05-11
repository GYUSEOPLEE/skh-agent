package kr.co.skh.agent.communication;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HelmetLocation {
    private LocalDateTime dateTime;
    private double latitude;
    private double longtitude;
    private String kickboardNo;
}
