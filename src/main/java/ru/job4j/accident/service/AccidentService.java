package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public void createAccident(Accident accident, String[] ids) {
        accidentMem.createAccident(accident, ids);
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }

    public Collection<AccidentType> findAccidentTypes() {
        return accidentMem.findAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentMem.findAccidentTypeById(id);
    }

    public Collection<Rule> findRules() {
        return accidentMem.findRules();
    }
}
