

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ActorRepository {

    private final EntityManager entityManager;

    public ActorRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Actor save(final Actor actor) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(actor);
            transaction.commit();
            return actor;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public Optional<Actor> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(Actor.class, id));
    }

    public List<Actor> findAllBornAfter(final int year) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE a.yearOfBirth > :year", Actor.class)
                .setParameter("year", year)
                .getResultList();
    }

    public List<Actor> findAllWithLastNameEndsWith(final String suffix) {
        return entityManager.createQuery("SELECT a FROM actors a WHERE a.lastName LIKE :lastName", Actor.class)
                .setParameter("lastName", "%" + suffix)
                .getResultList();
    }
}

