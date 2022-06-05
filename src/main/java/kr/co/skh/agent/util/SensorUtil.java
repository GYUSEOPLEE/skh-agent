package kr.co.skh.agent.util;

import com.pi4j.io.gpio.*;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.python.util.PythonInterpreter;
import org.springframework.scheduling.annotation.Async;

@Log4j2
public class SensorUtil extends Thread {
    private final GpioController gpio = GpioFactory.getInstance();
    private long rejectionTime;
    private GpioPinDigitalOutput pinTrig;
    private GpioPinDigitalInput pinEcho;
    private GpioPinDigitalOutput pinWarnNotice;

    public SensorUtil(int echo, int trig, int warnNotice, long rejectionTime) {
        this.rejectionTime = rejectionTime;

        pinTrig = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(trig), "pinTrig", PinState.HIGH);
        pinTrig.setShutdownOptions(true, PinState.LOW);

        pinEcho = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(echo), PinPullResistance.PULL_DOWN);

        pinWarnNotice = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(warnNotice), PinState.LOW);
    }
    // 초음파 센서 작동
    public int detectObject() {
        int distance;
        long startTime;
        long endTime;
        long time = 0;

        pinTrig.low();
        busyWaitMicros(2);

        pinTrig.high();
        startTime = System.nanoTime();
        busyWaitMicros(10);

        pinTrig.low();

        while (pinEcho.isLow()) {
        }

        while (pinEcho.isHigh()) {
            busyWaitNanos(1);
            time++;

            if (time == rejectionTime) {
                return 81; // 헬멧 착용 범위 80mm
            }
        }

        endTime = System.nanoTime();
        distance = (int) ((endTime - startTime) / 5882.35294118);

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
            log.debug("GPS 파이썬 코드 실행");
            Thread.sleep(1000);
        }
    }
}
