package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident();
        accident1.setId(1);
        accident1.setName("name1");
        accident1.setText("text1");
        accident1.setAddress("address1");

        Accident accident2 = new Accident();
        accident2.setId(2);
        accident2.setName("name2");
        accident2.setText("text2");
        accident2.setAddress("address2");

        accidents.put(1, accident1);
        accidents.put(2, accident2);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }
}