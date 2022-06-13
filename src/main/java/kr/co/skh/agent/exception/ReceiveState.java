package kr.co.skh.agent.exception;

import lombok.*;
import java.io.Serializable;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ReceiveState implements Serializable {
    private String code;
    private String message;
}
