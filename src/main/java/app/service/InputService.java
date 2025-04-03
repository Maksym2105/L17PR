package app.service;

import app.messages.GameShopMessages;
import app.messages.GameType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class InputService {

    private final Scanner scanner;
    private final OutputService outputService;

    public InputService(Scanner scanner, OutputService outputService) {
        this.scanner = scanner;
        this.outputService = outputService;
    }

    public String readString() {
        while(true) {
            String input = scanner.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            }
            outputService.printMessage(GameShopMessages.INPUT_BLANK.getMessage());
        }
    }

    public Double readDouble() {
        while(true) {
            String input = scanner.nextLine().trim();
            try{
                double value = Double.parseDouble(input);
                if(value > 0){
                    return value;
                }
            }catch(NumberFormatException e){
                outputService.printMessage(GameShopMessages.INPUT_DOUBLE.getMessage());
            }
        }
    }

    public Short readShort() {
        while(true) {
            String input = scanner.nextLine().trim();
            try{
                short value = Short.parseShort(input);
                if(value > 0){
                    return value;
                }
            } catch (NumberFormatException e) {
                outputService.printMessage(GameShopMessages.INPUT_SHORT.getMessage());
            }
        }
    }

    public GameType readGameType(){
        while(true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try{
                return Enum.valueOf(GameType.class, input);
            } catch (Exception e) {
                outputService.printMessage(GameShopMessages.ENTER_GAME_TYPE.getMessage() +
                        " " + Arrays.toString(GameType.class.getEnumConstants()));
            }
        }
    }

    public LocalDate readLocalDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while(true) {
            String input = scanner.nextLine().trim();
            try{
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
                outputService.printMessage(GameShopMessages.INPUT_DATE.getMessage());
            }
        }
    }
}