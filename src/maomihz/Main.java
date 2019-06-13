package maomihz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        for (int i = 0; i < 20; i++) {
            int r = (int)(Math.random() * 100000000);
            NumConverter conv = new NumConverter(r);
            System.out.print(r + ": ");
            System.out.println(conv);
        }

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Type a number ==> ");
            if (!scan.hasNextLine()) {
                System.out.println("Bye!");
                break;
            }
            String s = scan.nextLine();
            try {
                System.out.println(NumConverter.getWord(s));
            } catch (NumberFormatException e) {
                System.out.println("Error processing input");
            }
        }
    }
}
