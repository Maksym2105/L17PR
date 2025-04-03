package app.queries;

public class SQLQueries {

    public static final String ADD_NEW_GAME =
            """
            INSERT INTO games(
            name, release_date, rating, price, description, type)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String DELETE_BY_NAME =
            """
            DELETE FROM games
            WHERE name = ?
            """;

    public static final String SEARCH_GAME_BY_NAME =
            """
            SELECT * FROM games
            WHERE name = ?
            """;

    public static final String SHOW_ALL_GAMES =
            """
            SELECT * FROM games
            """;

    public static final String GET_GAMES_BY_PRICE =
            """
            SELECT * FROM games
            WHERE price = ?
            ORDER BY price ASC
            """;

    public static final String GET_GAMES_BY_TYPE =
            """
            SELECT * FROM games
            WHERE type = ?
            ORDER BY type ASC
            """;
}