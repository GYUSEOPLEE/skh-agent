package kr.co.skh.agent.device;

import kr.co.skh.agent.domain.HelmetLocation;
import kr.co.skh.agent.domain.HelmetWear;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {
    //GPIO 라이브러리는 Rasbpberry Pi 에서 작동시켜야 해서 주석처리 해놈
    /*private final GpioController gpio = GpioFactory.getInstance();
    //초음파 센서 기본 세팅 값
    private long rejectionStart;
    private long rejectionTime;
    //출력 핀
    private GpioPinDigitalOutput pinTrig;
    //입력 핀
    private GpioPinDigitalInput pinEcho;
    private GpioPinDigitalOutput pinWarnNotice;

    //생성자 기본 값 앞 3개는 핀 번호 뒤에 2개는 고정
    //0, 1, 2, 1000, 1000
    public AgentServiceImpl(int echo, int trig, int warnNotice, long rejectionStart, long rejectionTime) {
        this.rejectionStart = rejectionStart;
        this.rejectionTime = rejectionTime;

        pinTrig = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(trig), "pinTrig", PinState.HIGH);
        pinTrig.setShutdownOptions(true, PinState.LOW);

        pinEcho = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(echo), PinPullResistance.PULL_DOWN);

        pinWarnNotice = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(warnNotice), PinState.LOW);
    }

    //부저 코드
    public void warnNotice() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            pinWarnNotice.high();
            Thread.sleep(200);

            pinWarnNotice.low();
            Thread.sleep(200);
        }
    }

    //초음파 센서 코드
    public boolean detectObject() {
        int distance;
        long startTime;
        long endTime;
        long start = 0;
        long time = 0;

        pinTrig.low();
        busyWaitMicros(2);

        pinTrig.high();
        busyWaitMicros(10);

        pinTrig.low();

        while (pinEcho.isLow()) {
            busyWaitNanos(1);
            start++;

            if (start == rejectionStart) {
                return true;
            }
        }

        startTime = System.nanoTime();

        while (pinEcho.isHigh()) {
            busyWaitNanos(1);
            time++;

            if (time == rejectionTime) {
                return true;
            }
        }

        endTime = System.nanoTime();
        distance = (int) ((endTime - startTime) / 5882.35294118);

        return distance < 100;
    }

    private void busyWaitMicros(long micros) {
        long waitUntil = System.nanoTime() + (micros * 1_000);

        while (waitUntil > System.nanoTime()) {}
    }

    private void busyWaitNanos(long nanos) {
        long waitUntil = System.nanoTime() + nanos;
        while (waitUntil > System.nanoTime()) {}
    }*/


    //위 참조해서 아래 작성
    @Override
    public HelmetWear checkHelmetWear() {
        return null;
    }

    @Override
    public HelmetLocation checkHelmetLocation() {
        return null;
    }

    @Override
    public void warnHelmetNoWear() {

    }

    @Override
    public void warnHelmetLoss() {

    }
}
