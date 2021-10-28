package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(transactionManager = "transactionManager")
public class AccidentJpaService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentJpaService(AccidentRepository accidentRepository,
                              AccidentTypeRepository accidentTypeRepository,
                              RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Transactional
    public Iterable<Accident> getAccidents() {
        return accidentRepository.findAll();
    }

    @Transactional
    public Iterable<AccidentType> getAccidentTypes() {
        return accidentTypeRepository.findAll();
    }

    @Transactional
    public Iterable<Rule> getRules() {
        return ruleRepository.findAll();
    }

    @Transactional
    public void createAccident(Accident accident, String[] ids) {
        accident.setType(findAccidentTypeById(accident.getType().getId()));
        accident.setRules(findRulesById(ids));
        accidentRepository.save(accident);
    }

    @Transactional
    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id).orElse(null);
    }

    @Transactional
    public AccidentType findAccidentTypeById(int id) {
        return accidentTypeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Rule findRuleById(int id) {
        return ruleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Set<Rule> findRulesById(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(findRuleById(Integer.parseInt(id)));
        }
        return rules;
    }
}
