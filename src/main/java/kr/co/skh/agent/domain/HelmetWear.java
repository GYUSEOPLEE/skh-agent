package kr.co.skh.agent.domain;

import lombok.*;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class HelmetWear {
    private String helmetNo;
    @Builder.Default
    private String wear = "N";
}
