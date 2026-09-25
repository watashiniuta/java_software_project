package project4;
import java.io.IOException;

public class TEST4 {
	public static void main(String[] args) throws IOException {
		// --- 1. ObjectStackLimitedCapacity 테스트 ---
        System.out.println("=== 1. Limited Stack Test ===");
        ObjectStackLimitedCapacity limitedStack = new ObjectStackLimitedCapacity();
        for (int i = 1; i <= 10; i++) limitedStack.push(i);
        System.out.println("Size of 10 pushes: " + limitedStack.size()); // 10 출력

        // --- 2. ObjectStack (Unlimited) 테스트 - 1 ---
        System.out.println("\n=== 2. Unlimited Stack Tes (ObjectStack)t ===");
        ObjectStack unlimitedStack = new ObjectStack();
        for (int i = 1; i <= 15; i++) {
        	unlimitedStack.push("Item -> " + i);
        }
        System.out.println("Size of 15 pushes: " + unlimitedStack.size()); // 15 (자동 확장됨)
        System.out.println("Pop: " + unlimitedStack.pop()); // Item 15 출력
        System.out.println("Size of stack after pop: " + unlimitedStack.size()); // 14출력
        
        // --- 2. ObjectStack (Unlimited) 테스트 - 2 (int 형, String 형 push에 대한 테스트) ---
        System.out.println("\n=== 2. Mixed data Type Test (ObjectStack) ===");
        ObjectStack mixedDataStack = new ObjectStack();
        mixedDataStack.push(100);     // Integer
        mixedDataStack.push("String"); // String
        System.out.println("Pop 1: " + mixedDataStack.pop()); // String 출력
        System.out.println("Pop 2: " + mixedDataStack.pop()); // 100 출력
        
        // --- 3. ParaStack (Generic) 테스트 ---
        System.out.println("\n=== 3. ParaStack (Type-safe) Test ===");
        ParaStack<String> paraStack = new ParaStack<>();
        paraStack.push("Hello");
        paraStack.push("Java");
        // paraStack.push(10); // 컴파일 에러 발생 (타입 안전성 확인)
        System.out.println("Pop String: " + paraStack.pop()); // Java 출력
        
        // --- 4. ParaStackPeekN (N-th Peek) 테스트 ---
        System.out.println("\n=== 4. ParaStackPeekN Test ===");
        ParaStackPeekN<Integer> peekStack = new ParaStackPeekN<>();
        peekStack.push(10); // 3번째
        peekStack.push(20); // 2번째
        peekStack.push(30); // 1번째 (Top)
        System.out.println("Peek(1): " + peekStack.peek(1)); // 30 출력
        System.out.println("Peek(2): " + peekStack.peek(2)); // 20 출력
        System.out.println("Peek(3): " + peekStack.peek(3)); // 10 출력
        System.out.println("Current Size: " + peekStack.size()); // 3 출력 (데이터 유지 확인)
        
        // 개인 과제물 인증을 위한 불가피한 절차
        new ProcessBuilder("cmd", "/c", "echo %date%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "echo %time%").inheritIO().start();
        new ProcessBuilder("cmd", "/c", "whoami").inheritIO().start();
	}
}