package app.service;

import app.featureService.GameRepresentation;
import app.Starter;
import app.messages.GameShopMessages;
import app.messages.GameType;
import app.model.Game;

import java.util.Arrays;
import java.util.Optional;

public class GameShopMenu {

    private final GameShopServiceRealisation gameShopServiceRealisation;
    private final InputService inputService;
    private final OutputService outputService;
    private final GameCreator gameCreator;

    public GameShopMenu(GameShopServiceRealisation gameShopServiceRealisation,
                        InputService inputService, OutputService outputService) {
        this.gameShopServiceRealisation = gameShopServiceRealisation;
        this.inputService = inputService;
        this.outputService = outputService;
        this.gameCreator = new GameCreator(inputService, outputService);
    }

    public Starter addGame() {
        return  () -> {
                try {
                    if (gameShopServiceRealisation.addGame(gameCreator.createGame())) {
                        outputService.printMessage(GameShopMessages.GAME_SUCCESFULLY_ADDED.getMessage());
                    } else {
                        outputService.printMessage(GameShopMessages.GAME_UNFORTUNALLY_WAS_NOT_ADDED.getMessage());
                    }
                } catch (Exception e) {
                    outputService.printError(e.getMessage());
                }
        };
    }

    public Starter searchGameByName(){
        return  () -> {
            outputService.printMessage(GameShopMessages.ENTER_GAME_NAME.getMessage());
            String gameName = inputService.readString();
            try{
                Optional<Game> game = gameShopServiceRealisation.searchGameByName(gameName);
                if(game.isPresent()){
                    outputService.printMessage(GameRepresentation.gameRepresent(game.get()));
                }else{
                    outputService.printMessage(GameShopMessages.GAME_NAME_NOT_FOUND.getMessage());
                }
            } catch (Exception e) {
                outputService.printError(e.getMessage());
            }
        };
    }

    public Starter deleteGameByName(){
        return  () -> {
            outputService.printMessage(GameShopMessages.ENTER_GAME_NAME.getMessage());
            String gameName = inputService.readString();

            try{
                if(gameShopServiceRealisation.deleteGameByName(gameName)){
                    outputService.printMessage(GameShopMessages.GAME_SUCCESFULLY_DELETED.getMessage());
                }else{
                    outputService.printError(GameShopMessages.GAME_UNFORTUNALLY_WAS_NOT_DELETED.getMessage());
                }
            }catch (Exception e){
                outputService.printError(e.getMessage());
            }
        };
    }

    public Starter showAllGames(){
        return  () -> {
            try{
                gameShopServiceRealisation.showAllGames()
                        .forEach(game -> outputService.printMessage(GameRepresentation.gameRepresent(game)));
            }catch (Exception e){
                outputService.printError(e.getMessage());
            }
        };
    }

    public Starter getGamesByPrice(){
        return  () -> {
            outputService.printMessage(GameShopMessages.ENTER_GAME_PRICE.getMessage());

            try{
                gameShopServiceRealisation
                        .getGamesByPrice(inputService.readDouble())
                        .forEach(game -> outputService.printMessage(GameRepresentation.gameRepresent(game)));
            } catch (Exception e) {
                outputService.printError(e.getMessage());
            }
        };
    }

    public Starter getGamesByType(){
        return  () -> {
            outputService.printMessage(GameShopMessages.ENTER_GAME_TYPE.getMessage() +
                    " " + Arrays.toString(GameType.class.getEnumConstants()));
            try{
                gameShopServiceRealisation
                        .getGamesByType(inputService.readGameType())
                        .forEach(game -> outputService.printMessage(GameRepresentation.gameRepresent(game)));

            } catch (Exception e) {
                outputService.printError(e.getMessage());
            }
        };
    }

    public Starter menuExit(){
        return  () -> {
            try{
                outputService.printMessage(GameShopMessages.EXIT.getMessage());
                System.exit(1);
            }catch (Exception e){
                outputService.printError(e.getMessage());
            }

        };
    }
}