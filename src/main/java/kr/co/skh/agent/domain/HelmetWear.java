package kr.co.skh.agent.domain;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class HelmetWear implements Serializable {
    private String no;
    private char wear;
}
