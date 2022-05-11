package kr.co.skh.agent.communication;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class Helmet implements Serializable {
    private String no;
    private String model;
    private String ip;
    private String kickboardIp;
    private char activation;
    private char size;
}
