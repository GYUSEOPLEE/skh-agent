package kr.co.skh.agent.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
@Component @Scope("prototype")
public class HelmetWear {
    private String helmetNo;
    @Builder.Default
    private String wear = "N";
}
