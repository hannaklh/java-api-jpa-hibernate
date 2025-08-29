package com.booleanuk.api.controller;

import com.booleanuk.api.dto.GameDto;
import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(this.gameRepository.findAll());
    }
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameDto gameDto) {
        Game g = new Game(gameDto);
        this.gameRepository.save(g);
        return new ResponseEntity<Game>(g, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody GameDto gameDto) {
        Optional<Game> gameOptional = this.gameRepository.findById(id);
        if (gameOptional.isPresent()){
            Game g = gameOptional.get();
            g.setTitle(gameDto.getTitle());
            g.setGenre(gameDto.getGenre());
            g.setDeveloper(gameDto.getDeveloper());
            g.setReleaseYear(gameDto.getReleaseYear());
            g.setEarlyAccess(gameDto.isEarlyAccess());
            this.gameRepository.save(g);

            return new ResponseEntity<>(g, HttpStatus.CREATED);

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Game> deleteGame(@PathVariable int id) {
        Optional<Game> gameOptional = this.gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game g = gameOptional.get();
            this.gameRepository.delete(g);
            return ResponseEntity.ok(g);
        }
        return ResponseEntity.notFound().build();
    }
}
