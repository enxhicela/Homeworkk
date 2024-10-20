

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class MovieRepository {

    private final EntityManager entityManager;

    public MovieRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("FROM movies", Movie.class).getResultList();
    }

    public Optional<Movie> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));
    }

    public List<Movie> findAllByName(String title) {
        return entityManager.createQuery("SELECT m FROM movies m WHERE m.title = :title", Movie.class)
                .setParameter("title", title)
                .getResultList();
    }

    public Movie save(final Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.persist(movie);
            transaction.commit();
            return movie;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void remove(final Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            entityManager.remove(movie);
            transaction.commit();
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Movie> findAllWithActors() {
        return entityManager.createQuery("SELECT m FROM movies m LEFT JOIN FETCH m.actors", Movie.class)
                .getResultList();
    }
}

