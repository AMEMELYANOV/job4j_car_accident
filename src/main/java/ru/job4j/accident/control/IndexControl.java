package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> strings = new ArrayList<>();
        strings.add("String #1");
        strings.add("String #2");
        strings.add("String #3");

        model.addAttribute("user", "Petr Arsentev");
        model.addAttribute("strings", strings);
        return "index";
    }
}