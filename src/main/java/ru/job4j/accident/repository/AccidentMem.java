package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(2);
    private final Map<Integer, AccidentType> types = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setName("name1");
        accident1.setText("text1");
        accident1.setAddress("address1");
        accident1.setType(AccidentType.of(1, "Две машины"));

        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setName("name2");
        accident2.setText("text2");
        accident2.setAddress("address2");
        accident2.setType(AccidentType.of(2, "Машина и человек"));

        accidents.put(1, accident1);
        accidents.put(2, accident2);

        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void createAccident(Accident accident) {
        int id = accident.getId();
        AccidentType type = findAccidentTypeById(accident.getType().getId());
        accident.setType(type);
        if (!accidents.containsKey(id)) {
            id = counter.incrementAndGet();
            accident.setId(id);
        }
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
}