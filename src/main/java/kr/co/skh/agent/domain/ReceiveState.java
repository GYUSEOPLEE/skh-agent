package kr.co.skh.agent.domain;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class ReceiveState implements Serializable {
    private String code;
    private String message;
}
