package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident createAccident(Accident accident, String[] ids) {
        int currentId = accident.getId();
        if (findAccidentById(currentId) == null) {
        KeyHolder key = new GeneratedKeyHolder();
        String sql = "insert into accidents (name, text, address, accident_type_id)"
                + " values (?, ?, ?, ?)";
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, key);
        int id = (Integer) key.getKeyList().get(0).get("id");
        saveRulesForAccident(id, ids);
        accident.setId(id);
        }
        updateAccident(accident, ids);
        return accident;
    }

    private void updateAccident(Accident accident, String[] ids) {
        String sqlUpdate = "update accidents set name = ?, "
                + "text = ?, address = ?, accident_type_id = ? where id = ?";
        String sqlDelete = "delete from accidents_rules where accident_id = ?";

        jdbc.update(sqlUpdate,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
                );
        jdbc.update(sqlDelete,
                accident.getId()
        );

        saveRulesForAccident(accident.getId(), ids);
    }

    public List<Accident> getAccidents() {
        return jdbc.query("select * from accidents",
                (rs, row) -> {
                    int resultId = rs.getInt("id");
                    Accident accident = new Accident();
                    accident.setId(resultId);
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));

                    int typeId = rs.getInt("accident_type_id");

                    AccidentType type = findAccidentTypeById(typeId);
                    accident.setType(type);

                    Set<Rule> rules = new HashSet<>(findRulesByAccidentId(resultId));
                    accident.setRules(rules);
                    return accident;
                });
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from accident_types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public List<Rule> getRules() {
        return jdbc.query("select * from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public AccidentType findAccidentTypeById(int id) {
        try {
            return jdbc.queryForObject("select * from accident_types where id = ?",
                    new Object[]{id},
                    (rs, row) -> {
                        AccidentType accidentType = new AccidentType();
                        accidentType.setId(rs.getInt("id"));
                        accidentType.setName(rs.getString("name"));
                        return accidentType;
                    });
        } catch (Exception e) {
            return null;
        }
    }

    public Accident findAccidentById(int id) {
        try {
            return jdbc.queryForObject("select * from accidents where id = ?",
                    new Object[]{id},
                    (rs, row) -> {
                        int resultId = rs.getInt("id");
                        Accident accident = new Accident();
                        accident.setId(resultId);
                        accident.setName(rs.getString("name"));
                        accident.setText(rs.getString("text"));
                        accident.setAddress(rs.getString("address"));

                        int typeId = rs.getInt("accident_type_id");

                        AccidentType type = findAccidentTypeById(typeId);
                        accident.setType(type);

                        Set<Rule> rules = new HashSet<>(findRulesByAccidentId(resultId));
                        accident.setRules(rules);
                        return accident;
                    });
        } catch (Exception e) {
            return null;
        }
    }

    private List<Rule> findRulesByAccidentId(int id) {
        return jdbc.query("select rules.id, rules.name from accidents join accidents_rules "
                        + "on accidents_rules.accident_id = accidents.id "
                        + "join rules on rules.id = accidents_rules.rules_id "
                        + "where accidents.id = (?)",
                new Object[]{id},
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    private void saveRulesForAccident(int id, String[] ids) {
        for (String s : ids) {
            jdbc.update("insert into accidents_rules (accident_id, rules_id)"
                            + " values (?, ?)",
                    id, Integer.parseInt(s));
        }
    }
}