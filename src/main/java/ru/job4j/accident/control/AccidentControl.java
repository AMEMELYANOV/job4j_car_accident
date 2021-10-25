package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentHibernateService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentHibernateService service;

    public AccidentControl(AccidentHibernateService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAccidentTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        String[] ids = request.getParameterValues("rIds");
        service.createAccident(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("types", service.getAccidentTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/update";
    }
}