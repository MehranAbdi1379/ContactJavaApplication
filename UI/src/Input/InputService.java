package Input;

import java.util.Scanner;

public class InputService {
    protected static Scanner scanner = new Scanner(System.in);
    public static String readLine(){
        var input = scanner.nextLine();
        return input;
    }
    public static void closeScanner(){
        scanner.close();
    }
}
