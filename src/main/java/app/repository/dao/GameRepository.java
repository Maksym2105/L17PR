package app.repository.dao;

import app.messages.GameType;
import app.model.Game;
import java.util.List;
import java.util.Optional;

public interface GameRepository {

    boolean addGame(Game game);

    Optional<Game> searchGameByName(String name);

    boolean deleteGameByName(String name);

    List<Game> showAllGames();

    List<Game> getGamesByPrice(double price);

    List<Game> getGamesByType(GameType type);

}
