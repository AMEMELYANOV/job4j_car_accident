package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public String askStr() {
        System.out.print("Please enter data: ");
        return scanner.nextLine();
    }
}