package kr.co.skh.agent.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Kickboard {
    @NotNull(message = "입력값이 null 이에요") private String no;
    @NotBlank(message = "입력값이 존재하지 않아요") private String use;
}
