import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        var entityManager = sessionFactory.createEntityManager();


        final GenreRepository genreRepository = new GenreRepository(entityManager);
        final ActorRepository actorRepository = new ActorRepository(entityManager);
        final MovieRepository movieRepository = new MovieRepository(entityManager);


        Genre genre = new Genre(null, "Action", null);
        genreRepository.save(genre);

        Actor actor = new Actor(null, "John", "Smith", 1970, null);
        actorRepository.save(actor);

        Movie movie = new Movie(null, "Predator", 1980, List.of(actor), genre);
        movieRepository.save(movie);

        System.out.println(movieRepository.findAllWithActors().get(0).getActors().size()); // Example output
    }
}

