package kr.co.skh.agent.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
@Component
public class HelmetWear implements Serializable {
    private String no;
    private String wear;
}
