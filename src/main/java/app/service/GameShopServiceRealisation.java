package app.service;

import app.messages.GameType;
import app.model.Game;
import app.repository.dao.GameRepository;
import app.service.dao.GameShopService;

import java.util.List;
import java.util.Optional;

public class GameShopServiceRealisation implements GameShopService {

    private final GameRepository gameRepository;

    public GameShopServiceRealisation(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public boolean addGame(Game game) {
        return gameRepository.addGame(game);
    }

   @Override
    public Optional<Game> searchGameByName(String name) {
        return gameRepository.searchGameByName(name);
    }

    @Override
    public boolean deleteGameByName(String name){
        return gameRepository.deleteGameByName(name);
    }

    public List<Game> showAllGames(){
        return gameRepository.showAllGames();
    }

    public List<Game> getGamesByPrice(double price){
        return gameRepository.getGamesByPrice(price);
    }

    public List<Game> getGamesByType(GameType gameType){
        return gameRepository.getGamesByType(gameType);
    }
}