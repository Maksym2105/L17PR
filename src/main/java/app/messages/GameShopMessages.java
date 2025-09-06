package app.messages;

public enum GameShopMessages {

    ENTER_GAME_DESCRIPTION("Please enter the game description:"),
    ENTER_GAME_NAME("Please enter the game name:"),
    ENTER_GAME_PRICE("Please enter the game price(for example 99.99):"),
    ENTER_GAME_RATING("Please enter the game rating(for example - general 5, 6, 7, no need to type 5.79):"),
    ENTER_GAME_TYPE("Please enter the game type:"),
    EXIT("Thank you for visiting GameShop!"),
    GAME_NAME_NOT_FOUND("Game name not found"),
    GAME_SUCCESFULLY_ADDED("Game successfully added"),
    GAME_SUCCESFULLY_DELETED("Game successfully deleted"),
    GAME_UNFORTUNALLY_WAS_NOT_ADDED("Game unfortunally was not added"),
    GAME_UNFORTUNALLY_WAS_NOT_DELETED("Game unfortunally was not deleted"),
    INPUT_BLANK("Please type something..."),
    INPUT_DATE("Please enter the game release date in format DD/MM/YYYY:"),
    INPUT_SHORT("Please enter the short numbers only"),
    INPUT_DOUBLE("Please enter the double numbers only"),
    TEXT_FOR_CLIENT("""
            Choose one of the following options:\s
            add game - for adding new game to game shop\s
            find game by name - for finding game by name\s
            delete game by name - for deleting game by name\s
            show all remaining games - for showing all games at game shop\s
            show all games by price - for showing all games at game shop\s
            show all games by type - for showing all games at game shop\s
            exit - for exit"""),
    TYPE_THE_COMMAND("Type the command to execute(add game, show all and e.t"),
    WELCOME_TO_GAME_SHOP("Welcome to the game shop!");


    private final String message;

    GameShopMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
