package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;
import java.util.Set;

@Service
@Transactional(transactionManager = "htx")
public class AccidentHibernateService {

    private final AccidentHibernate accidentHibernate;

    public AccidentHibernateService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public List<Accident> getAccidents() {
        return accidentHibernate.getAccidents();
    }

    public void createAccident(Accident accident, String[] ids) {
        accident.setType(findAccidentTypeById(accident.getType().getId()));
        accident.setRules(findRulesById(ids));
        accidentHibernate.createAccident(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentHibernate.findAccidentById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentHibernate.getAccidentTypes();
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentHibernate.findAccidentTypeById(id);
    }

    private Set<Rule> findRulesById(String[] ids) {
        return accidentHibernate.findRulesById(ids);
    }

    public List<Rule> getRules() {
        return accidentHibernate.getRules();
    }

}
