package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import java.util.List;

@Service
public class AccidentJdbcService {

    private final AccidentJdbcTemplate accidentJdbc;

    public AccidentJdbcService(AccidentJdbcTemplate accidentJdbc) {
        this.accidentJdbc = accidentJdbc;
    }

    public List<Accident> getAccidents() {
        return accidentJdbc.getAccidents();
    }

    public void createAccident(Accident accident, String[] ids) {
        accidentJdbc.createAccident(accident, ids);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbc.findAccidentById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentJdbc.getAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentJdbc.findAccidentTypeById(id);
    }

    public List<Rule> getRules() {
        return accidentJdbc.getRules();
    }

}
