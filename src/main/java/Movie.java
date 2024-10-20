
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "actors", callSuper = true)
@ToString(exclude = "actors")
public class Movie extends BaseEntity {

    public Movie(Long id, String title, int yearOfRelease, List<Actor> actors, Genre genre) {
        super(id);
        this.title = title;
        this.yearOfRelease = yearOfRelease;
        this.actors = actors;
        this.genre = genre;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors = new ArrayList<>();

    @ManyToOne
    private Genre genre;
}

