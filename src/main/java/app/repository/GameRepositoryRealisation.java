package app.repository;

import app.exceptions.SQLOperationException;
import app.messages.GameType;
import app.model.Game;
import app.repository.dao.GameRepository;
import app.queries.SQLQueries;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryRealisation implements GameRepository {

    private final Connection connection;

    public GameRepositoryRealisation(Connection connection) {this.connection = connection;}

    private List<Game> mapToList(PreparedStatement preparedStatement) throws SQLException {
        List<Game> result = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result.add(mapGame(resultSet));
            }
        }
        return result;
    }

    private Game mapGame(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .release_date(resultSet.getObject("release_date", LocalDate.class))
                .rating(resultSet.getShort("rating"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("description"))
                .type(GameType.valueOf(resultSet.getString("type")))
                .creation_date(resultSet.getObject("creation_date", LocalDateTime.class))
                .build();
    }

    @Override
    public boolean addGame(Game game) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_NEW_GAME)){
            preparedStatement.setString(1, game.getName());
            preparedStatement.setDate(2, Date.valueOf(game.getRelease_date()));
            preparedStatement.setShort(3, game.getRating());
            preparedStatement.setDouble(4, game.getPrice());
            preparedStatement.setString(5, game.getDescription());
            preparedStatement.setString(6, game.getType().toString().toUpperCase());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                return false;
            }

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    game.setId(generatedKeys.getInt(7));
                }
            }
            return true;

        } catch (SQLException e) {
            throw new SQLOperationException("Cannot add new game " + e.getMessage());
        }
    }

    @Override
    public boolean deleteGameByName(String name) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_BY_NAME)){
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLOperationException("Cannot delete game " + e.getMessage());
        }
    }

    @Override
    public Optional<Game> searchGameByName(String name) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SEARCH_GAME_BY_NAME)){
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()) {
                    return Optional.of(mapGame(resultSet));
                }else{
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new SQLOperationException("Cannot get game by name " + e.getMessage());
        }
    }

    @Override
    public List<Game> getGamesByPrice(double price) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_GAMES_BY_PRICE)){
            preparedStatement.setDouble(1, price);
            return mapToList(preparedStatement);

        } catch (SQLException e) {
            throw new SQLOperationException("Cannot get game by price " + e.getMessage());
        }
    }

    @Override
    public List<Game> getGamesByType(GameType type) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_GAMES_BY_TYPE)){
            preparedStatement.setString(1, type.toString());
            return mapToList(preparedStatement);

        } catch (SQLException e) {
            throw new SQLOperationException("Cannot get game by type " + e.getMessage());
        }
    }

    @Override
    public List<Game> showAllGames() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SHOW_ALL_GAMES)){
            return mapToList(preparedStatement);

            } catch (SQLException e) {
                throw new SQLOperationException("Cannot get all game list " + e.getMessage());
            }
    }
}
