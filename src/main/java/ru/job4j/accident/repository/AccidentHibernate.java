package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Accident> getAccidents() {
        return tx(session -> session
                .createQuery("select distinct a "
                                + "from Accident a join fetch a.type "
                                + "join fetch a.rules",
                        Accident.class)
                .list());
    }

    public Accident createAccident(Accident accident) {
        return tx(session -> {
                    session.saveOrUpdate(accident);
                    return accident;
                });
    }

    public Accident findAccidentById(int id) {
        return tx(session -> session
                .createQuery("from Accident a where a.id = :id",
                        Accident.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public AccidentType findAccidentTypeById(int id) {
        return tx(session -> session
                .createQuery("from AccidentType a where a.id = :id",
                        AccidentType.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public Set<Rule> findRulesById(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(
                    tx(session -> session
                            .createQuery("from Rule r where r.id = :id", Rule.class)
                            .setParameter("id", Integer.parseInt(id))
                            .uniqueResult()
                    ));
        }
        return rules;
    }

    private Rule findRuleById(int id) {
        return tx(session -> session
                .createQuery("from Rule r where r.id = :id", Rule.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public List<AccidentType> getAccidentTypes() {
        return tx(session -> session
                .createQuery("from AccidentType a order by a.id",
                        AccidentType.class).list());
    }

    public List<Rule> getRules() {
        return tx(session -> session
                .createQuery("from Rule r order by r.id", Rule.class)
                .list());
    }
}