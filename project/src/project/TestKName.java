package project;
import java.io.IOException;

public class TestKName {

	public static void main(String[] args) throws IOException {
		Kname kname1 = new Kname("Hong", "Gildong");
		Kname kname2 = new Kname("Kim", "jihoon");
		
		System.out.println("compare first name is same (equal method): " + kname1.equal(kname2));
		
		System.out.println("confirm last name is Kim (isKim method): " + kname2.isKim());

		kname1.printEnglishStyle();
	
		// 실명/ID, 실행 날짜 및 시간을 표기 하기 위한 코드
        new ProcessBuilder("cmd", "/c", "echo %date%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "echo %time%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "whoami").inheritIO().start();
	}
}