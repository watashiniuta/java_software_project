package project3;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TEST {

	public static void main(String[] args) {
		
		/*
		String[] s = {null, null};
		
		System.out.println(s[0].isEmpty());
		*/
		
		/*
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		String formattedDate = now.format(formatter);
		System.out.println(formattedDate); // 출력: 2024년 05월 20일 14:30:05 (예시)
		*/
		
		File file = new File("./src/schedule-normal.data");
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		

				
		
		/*
		String s = new String("TITLE=");
		String[] str = s.split("=");
		
		System.out.println(str[1]);
		*/

		
		/*
		// 현재 시간 출력 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// 1. 포맷팅 (객체 -> 문자열)
		String formatted = now.format(formatter); 

		// 2. 파싱 (문자열 -> 객체)
		LocalDateTime parsed = LocalDateTime.parse("2024-04-04 15:30:00", formatter);
		
		System.out.println(formatted);
		System.out.println(parsed);
		*/
		
		/*
		File file = new File("./src/schedule-normal.data");
	
		try (Scanner scanner = new Scanner(file, "MS949")) {
			System.out.println("file has reading...");
			
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
