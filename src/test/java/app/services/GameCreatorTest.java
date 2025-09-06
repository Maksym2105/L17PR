package app.services;

import app.messages.GameType;
import app.model.Game;
import app.service.GameCreator;
import app.service.InputService;
import app.service.OutputService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameCreatorTest {

    private InputService inputService;
    private OutputService outputService;
    private GameCreator gameCreator;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @BeforeEach
    public void setUp() {
        inputService = mock(InputService.class);
        outputService = mock(OutputService.class);
        gameCreator = new GameCreator(inputService, outputService);
    }

    @Test
    public void createGameValidInputTest() {
        when(inputService.readString()).thenReturn("Diablo II");
        when(inputService.readLocalDate()).thenReturn(LocalDate.parse("28-06-2000", formatter));
        when(inputService.readShort()).thenReturn((short) 9);
        when(inputService.readDouble()).thenReturn(19.99);
        when(inputService.readGameType()).thenReturn(GameType.ACTION);

        Game game = gameCreator.createGame();

        assertNotNull(game);
        assertEquals("Diablo II", game.getName());
        assertEquals(LocalDate.parse("28-06-2000", formatter), game.getRelease_date());
        assertEquals(9, game.getRating());
        assertEquals(19.99, game.getPrice());
        assertEquals(GameType.ACTION, game.getType());

        verify(outputService, times(6)).printMessage(anyString());
    }

    @Test
    public void createGameInvalidInputTest() {
        when(inputService.readString()).thenReturn("");
        when(inputService.readLocalDate()).thenThrow(new IllegalArgumentException("Invalid input"));
        when(inputService.readShort()).thenReturn((short) -10);
        when(inputService.readDouble()).thenReturn(-143.98);
        when(inputService.readGameType()).thenThrow(new IllegalArgumentException("Invalid input"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> gameCreator.createGame());

        assertTrue(exception.getMessage().contains("Invalid input"));
    }

    @Test
    public void invalidDateTest() {
        when(inputService.readLocalDate()).thenThrow(new IllegalArgumentException("Invalid date format"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> gameCreator.createGame());
        assertTrue(exception.getMessage().contains("Invalid date format"));
    }
}