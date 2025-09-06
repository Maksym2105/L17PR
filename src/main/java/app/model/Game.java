package app.model;

import app.messages.GameType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder

public class Game {
    private int id;
    private String name;
    private LocalDate release_date;
    private short rating;
    private double price;
    private String description;
    private GameType type;
    private LocalDateTime creation_date;

}