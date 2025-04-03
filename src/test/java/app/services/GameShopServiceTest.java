package app.services;

import app.messages.GameType;
import app.model.Game;
import app.repository.GameRepositoryRealisation;
import app.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameShopServiceTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mock
    private GameRepositoryRealisation gameRepositoryRealisation;
    @Mock
    private GameShopServiceRealisation gameShopServiceRealisation;

    private List<Game> gameList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        this.gameList = new ArrayList<>(List.of(Game.builder()
                .id(1)
                .name("Portal 2")
                .release_date(LocalDate.parse("29-06-2010", formatter))
                .rating((short) 9)
                .price(29.99)
                .description("Sequel of original portal")
                .type(GameType.PUZZLE)
                .creation_date(LocalDateTime.now())
                .build(),
        Game.builder()
                .id(2)
                .name("Fallout New Vegas")
                .release_date(LocalDate.parse("19-10-2010", formatter))
                .rating((short) 8)
                .price(34.99)
                .description("Post-Apocalypse RPG")
                .type(GameType.RPG)
                .creation_date(LocalDateTime.of(2024, 11, 25, 23, 54, 49))
                .build()));

        gameShopServiceRealisation = new GameShopServiceRealisation(gameRepositoryRealisation);
    }

    @Test
    public void addGame() {
        Game game = gameList.getFirst();

        when(gameRepositoryRealisation.addGame(game)).thenReturn(true);

        assertTrue(gameShopServiceRealisation.addGame(game));

        verify(gameRepositoryRealisation, times(1)).addGame(game);
    }

    @Test
    public void searchGameByName() {
        MockitoAnnotations.openMocks(this);

        Game game = gameList.get(1);

        when(gameShopServiceRealisation.searchGameByName("Fallout New Vegas")).thenReturn(Optional.of(game));

        Optional<Game> result = gameShopServiceRealisation.searchGameByName("Fallout New Vegas");

        assertTrue(result.isPresent());

        assertEquals("Fallout New Vegas", result.get().getName());

        verify(gameShopServiceRealisation, times(1)).searchGameByName("Fallout New Vegas");
    }

    @Test
    public void searchGameByNameFail(){
        Optional<Game> result = gameShopServiceRealisation.searchGameByName("");

        assertFalse(result.isPresent());
    }

    @Test
    public void deleteGameByName() {
        String gameName = "Fallout New Vegas";

        when(gameShopServiceRealisation.deleteGameByName(gameName)).thenReturn(true);

        assertTrue(gameShopServiceRealisation.deleteGameByName(gameName));

        verify(gameRepositoryRealisation, times(1)).deleteGameByName(gameName);
    }

    @Test
    public void deleteGameByNameFail(){
       String gameName = "";

       boolean result = gameShopServiceRealisation.deleteGameByName(gameName);

       assertFalse(result);
    }

    @Test
    public void searchGameByPrice() {
        when(gameShopServiceRealisation.getGamesByPrice(29.99)).thenReturn(List.of(gameList.get(0)));

        List<Game> result = gameShopServiceRealisation.getGamesByPrice(29.99);

        String expected = "Portal 2";

        assertEquals(1, result.size());
        assertEquals(expected, result.get(0).getName());

    }

    @Test
    public void searchGameByPriceFail(){
        when(gameShopServiceRealisation.getGamesByPrice(34.99)).thenReturn(List.of());

        List<Game> result = gameShopServiceRealisation.getGamesByPrice(34.99);

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());

    }

    @Test
    public void showAllGames() {
        Game game = gameList.get(0);

        when(gameShopServiceRealisation.showAllGames()).thenReturn(List.of(game));

        List<Game> result = gameShopServiceRealisation.showAllGames();

        assertEquals(1, result.size());
    }

    @Test
    public void showGamesByType(){

        Game game = gameList.get(1);

        when(gameShopServiceRealisation.getGamesByType(GameType.RPG)).thenReturn(List.of(game));

        List<Game> result = gameShopServiceRealisation.getGamesByType(GameType.RPG);

        assertEquals(1, result.size());
        assertEquals(GameType.RPG, result.get(0).getType());

    }

    @Test
    public void showGamesByTypeFail(){

        Game game = gameList.get(1);
        when(gameShopServiceRealisation.getGamesByType(GameType.RPG)).thenReturn(List.of());

        List<Game> result = gameShopServiceRealisation.getGamesByType(GameType.RPG);

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }
}