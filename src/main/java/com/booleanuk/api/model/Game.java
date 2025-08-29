package com.booleanuk.api.model;

import com.booleanuk.api.dto.GameDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
    @Column(name = "developer")
    private String developer;
    @Column(name="release_year")
    private int releaseYear;
    @Column(name="is_early_access")
    private boolean isEarlyAccess;

    public Game(GameDto gameDto) {
        setTitle(gameDto.getTitle());
        setGenre((gameDto.getGenre()));
        setDeveloper(gameDto.getDeveloper());
        setReleaseYear(gameDto.getReleaseYear());
        setEarlyAccess(gameDto.isEarlyAccess());
    }
}
