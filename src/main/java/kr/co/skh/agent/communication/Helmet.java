package kr.co.skh.agent.communication;

import lombok.Data;

@Data
public class Helmet {
    private String no;
    private String model;
    private String ip;
    private String kickboardIp;
    private char activation;
    private char size;
}
