package app.service;

import app.messages.GameShopMessages;
import app.messages.GameType;
import app.model.Game;

import java.util.Arrays;

public class GameCreator {

    private final InputService inputService;
    private final OutputService outputService;

    public GameCreator(InputService inputService, OutputService outputService) {
        this.inputService = inputService;
        this.outputService = outputService;
    }

    public Game createGame() {
        Game.GameBuilder builder = Game.builder();

        outputService.printMessage(GameShopMessages.ENTER_GAME_NAME.getMessage());
        builder.name(inputService.readString());

        outputService.printMessage(GameShopMessages.INPUT_DATE.getMessage());
        builder.release_date(inputService.readLocalDate());

        outputService.printMessage(GameShopMessages.ENTER_GAME_RATING.getMessage());
        builder.rating(inputService.readShort());

        outputService.printMessage(GameShopMessages.ENTER_GAME_PRICE.getMessage());
        builder.price(inputService.readDouble());

        outputService.printMessage(GameShopMessages.ENTER_GAME_DESCRIPTION.getMessage());
        builder.description(inputService.readString());

        outputService.printMessage(GameShopMessages.ENTER_GAME_TYPE.getMessage() +
                " " + Arrays.toString(GameType.class.getEnumConstants()));
        builder.type(inputService.readGameType());
        return builder.build();

    }


}