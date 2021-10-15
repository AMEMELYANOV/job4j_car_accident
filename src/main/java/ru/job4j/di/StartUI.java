package ru.job4j.di;

import org.springframework.stereotype.Component;

@Component
public class StartUI {
    private ConsoleInput input;
    private Store store;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

    public void input() {
        store.add(input.askStr());
    }
}