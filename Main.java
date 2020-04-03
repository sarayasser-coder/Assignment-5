package eg.edu.alexu.csd.datastructure.stack.cs;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stack S = new Stack();
        Scanner scanner = new Scanner(System.in);
        String choice;
        String element;

        while (true) {
        System.out.println("Choose an action: \n");
        System.out.println("1-Push to the stack\n2-Pop from the stack\n3-Peak of stack\n4-Size of stack\n5-Check if the stack is empty\n");
        choice = scanner.next();
        if (!Character.isDigit(choice.charAt(0))){
            throw new RuntimeException("Invalid Input!!");
        }
        int ch = Integer.parseInt(choice);
            switch (ch) {
                case 1:
                    System.out.println("Enter your element: \n");
                    element = scanner.next();
                    S.push(element);
                    System.out.println(element + " is pushed\n");
                    break;
                case 2:
                    System.out.println(S.pop() + " is popped\n");
                    break;
                case 3:
                    System.out.println("The peak is " + S.peek() + "\n");
                    break;
                case 4:
                    System.out.println("Size = " + S.size());
                    break;
                case 5:
                    System.out.println(S.isEmpty());
                    break;
                default:
                    throw new RuntimeException("Invalid Input!!");
            }

        }
    }
}