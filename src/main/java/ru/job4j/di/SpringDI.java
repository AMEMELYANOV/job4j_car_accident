package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI ui1 = context.getBean(StartUI.class);
        StartUI ui2 = context.getBean(StartUI.class);

        ui1.add("Petr Arsentev");
        ui1.add("Ivan ivanov");
        ui2.add("Michail Michailov");
        ui2.add("Oleg Sidorov");

        System.out.println("Output UI1");
        ui1.print();

        System.out.println();

        System.out.println("Output UI2");
        ui2.print();
    }
}