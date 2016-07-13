package ua.com.juja.sqlcmd.view;

import java.util.Scanner;

/**
 * Created by asu06 on 13.07.16.
 */
public class Console implements View {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
