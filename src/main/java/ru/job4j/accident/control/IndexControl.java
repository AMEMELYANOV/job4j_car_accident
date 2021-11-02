package ru.job4j.accident.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentJpaService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    private final AccidentJpaService service;

    public IndexControl(AccidentJpaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> res = new ArrayList<>();
        service.getAccidents().forEach(res::add);
        model.addAttribute("accidents", res);
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "index";
    }
}