package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem accidentMem = new AccidentMem();

        model.addAttribute("accidents", accidentMem.getAccidents());
        return "index";
    }
}