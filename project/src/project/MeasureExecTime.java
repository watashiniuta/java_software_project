package project;

import java.io.IOException;

public class MeasureExecTime {

    static long power(long num, long exp) {
        long result = 1;

        for(int i = 0; i < exp; i++) {
            result *= num;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        long startTime, endTime, execTime, N = 100000;

        startTime = System.nanoTime();
        for(int i = 0 ; i < N; i++) {
            power(7, 11);
        }
        endTime = System.nanoTime();
        execTime = endTime - startTime;

        System.out.println("(NotePad Editor)Execute of Time in nano seconds: " + (double)execTime/N);

        // 실명/ID, 실행 날짜 및 시간을 표기 하기 위한 코드
        new ProcessBuilder("cmd", "/c", "echo %date%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "echo %time%").inheritIO().start();
    }
}