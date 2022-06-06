package kr.co.skh.agent.util;

import com.pi4j.io.gpio.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;

@Log4j2
public class SensorUtil extends Thread {
    private static final GpioController gpio = GpioFactory.getInstance();
    private static GpioPinDigitalOutput pinTrig = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pintTrig", PinState.HIGH);
    private static GpioPinDigitalInput pinEcho = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);
    private static GpioPinDigitalOutput pinWarnNotice = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW);

    public SensorUtil() {
        pinTrig.setShutdownOptions(true, PinState.LOW);
    }
    // 초음파 센서 작동
    public int detectObject() {
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

            if (start == 1000) {
                log.info("rejectionStart 주의 max 1000 -> " + start);
                return 81;
            }
        }
        startTime = System.nanoTime();

        while (pinEcho.isHigh()) {
            busyWaitNanos(1);
            time++;

            if (time == 235229411) {
                log.info("rejectionTime 주의 max 235229411 ->" + time);
                return 81; // 헬멧 착용 범위 80mm
            }
        }

        endTime = System.nanoTime();
        distance = (int) ((endTime - startTime) / 5882.35294118);

        log.info("초음파 측정 거리 " + distance);
        return distance;
    }

    private void busyWaitMicros(long micros) {
        long waitUntil = System.nanoTime() + (micros * 1_000);
        while (waitUntil > System.nanoTime()) {}
    }

    private void busyWaitNanos(long nanos) {
        long waitUntil = System.nanoTime() + nanos;
        while (waitUntil > System.nanoTime()) {}
    }

    // 부저 실행
    public void buzzer() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            pinWarnNotice.high();
            Thread.sleep(200);

            pinWarnNotice.low();
            Thread.sleep(200);
        }
    }

    @Async
    public void kickboardLocation() {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("tgs.py");
        pythonInterpreter.exec("main()");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            kickboardLocation();
            log.info("GPS 파이썬 코드 실행");
            Thread.sleep(1000);
        }
    }
}
