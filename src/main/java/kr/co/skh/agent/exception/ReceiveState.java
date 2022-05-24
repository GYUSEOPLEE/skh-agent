package kr.co.skh.agent.exception;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReceiveState implements Serializable {
    private String code;
    private String message;
}
