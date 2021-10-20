package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(2);
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setName("name1");
        accident1.setText("text1");
        accident1.setAddress("address1");
        accident1.setType(AccidentType.of(1, "Две машины"));
        Set<Rule> set1 = new LinkedHashSet<>();
        set1.add(Rule.of(1, "Статья. 1"));
        accident1.setRules(set1);

        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setName("name2");
        accident2.setText("text2");
        accident2.setAddress("address2");
        accident2.setType(AccidentType.of(2, "Машина и человек"));
        Set<Rule> set2 = new LinkedHashSet<>();
        set2.add(Rule.of(2, "Статья. 2"));
        set2.add(Rule.of(3, "Статья. 3"));
        accident2.setRules(set2);

        accidents.put(1, accident1);
        accidents.put(2, accident2);

        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void createAccident(Accident accident, String[] ids) {
        int id = accident.getId();
        AccidentType type = findAccidentTypeById(accident.getType().getId());
        accident.setType(type);
        if (!accidents.containsKey(id)) {
            id = counter.incrementAndGet();
            accident.setId(id);
        }
        accident.setRules(getRulesForAccident(ids));
        accidents.put(id, accident);
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getAccidentTypes() {
        return types.values();
    }

    public AccidentType findAccidentTypeById(int id) {
        return types.get(id);
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }

    private Set<Rule> getRulesForAccident(String[] ids) {
        Set<Rule> rules = new LinkedHashSet<>();
        for (String id : ids) {
            rules.add(findRuleById(Integer.parseInt(id)));
        }
        return rules;
    }
}