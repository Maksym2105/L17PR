package app.featureService;

import app.model.Game;

import java.time.format.DateTimeFormatter;

public class GameRepresentation {

    public static String gameRepresent(Game game) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return "................................" + "\n" +
                "Game id " + game.getId() + "\n" +
                "Name " + game.getName() + "\n" +
                "Release date " + game.getRelease_date().format(formatter) + "\n" +
                "Rating " + game.getRating() + "\n" +
                "Price " + game.getPrice() + "\n" +
                "Description " + game.getDescription() + "\n" +
                "Type " + game.getType() + "\n" +
                "Creation date " + game.getCreation_date().format(fullFormatter) + "\n" +
                "...............................";


    }

}