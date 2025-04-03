package app;

import app.featureService.ConnectionSupplier;
import app.messages.GameShopMessages;
import app.repository.GameRepositoryRealisation;
import app.service.*;

import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        try(var connectionSupplier = ConnectionSupplier.getConnection();
            Scanner scanner = new Scanner(System.in)) {

            GameRepositoryRealisation gameRepository = new GameRepositoryRealisation(connectionSupplier);
            GameShopServiceRealisation gameShopServiceRealisation = new GameShopServiceRealisation(gameRepository);
            OutputService outputService = new OutputService(System.out, System.err);
            InputService inputService = new InputService(scanner, outputService);

            Map<String, Starter> orchestrator = initialize(inputService, outputService, gameShopServiceRealisation);

            outputService.printMessage(GameShopMessages.WELCOME_TO_GAME_SHOP.getMessage());
            String choice;

            do{
                outputService.printMessage(GameShopMessages.TEXT_FOR_CLIENT.getMessage());

                choice = inputService.readString();
                orchestrator.getOrDefault(choice, () -> outputService.printMessage(GameShopMessages.TYPE_THE_COMMAND.getMessage()))
                        .run();

            }while(!choice.equals("exit"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Starter> initialize(
            InputService inputService, OutputService outputService, GameShopServiceRealisation gameShopService) {
        GameShopMenu gameShopMenu = new GameShopMenu(gameShopService, inputService, outputService);

        return Map.of("add game", gameShopMenu.addGame(),
                "find game by name", gameShopMenu.searchGameByName(),
                "delete game by name", gameShopMenu.deleteGameByName(),
                "show all remaining games", gameShopMenu.showAllGames(),
                "show all games by price", gameShopMenu.getGamesByPrice(),
                "show all games by type", gameShopMenu.getGamesByType(),
                "exit", gameShopMenu.menuExit());
    }
}