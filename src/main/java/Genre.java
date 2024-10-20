

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "movies", callSuper = true)
@ToString(exclude = "movies")
public class Genre extends BaseEntity {

    private String name;


    public Genre(Long id, String name, List<Movie> movies) {
        super(id);
        this.name = name;
        this.movies = movies;
    }

    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Movie> movies;


}

