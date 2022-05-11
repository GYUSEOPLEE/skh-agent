package kr.co.skh.agent.communication;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class HelmetLocation implements Serializable {
    private LocalDateTime dateTime;
    private double latitude;
    private double longtitude;
    private String kickboardNo;
}
