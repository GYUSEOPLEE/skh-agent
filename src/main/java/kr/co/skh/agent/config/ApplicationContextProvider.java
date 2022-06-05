package kr.co.skh.agent.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

// 멀티 쓰레드에서 빈객체를 사용하기 위하여 설정
public class ApplicationContextProvider implements ApplicationContextAware {
    //스프링 제어권을 가지고 BeanFactory를 상속받고 있는 Context
    //컨테이너가 실행될때 빈을 Pre-loading
    private  static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        return  applicationContext;
    }
}
