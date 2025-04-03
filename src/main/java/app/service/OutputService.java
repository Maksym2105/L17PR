package app.service;

import java.io.PrintStream;

public class OutputService {

    PrintStream out;
    PrintStream err;

    public OutputService(PrintStream out, PrintStream err) {
        this.out = out;
        this.err = err;
    }

    public void printMessage(String message) {
        out.println(message);
    }

    public void printMessage(Object message) {
        out.println(message);
    }

    public void printMessage(String format, Object... args) {
        out.printf(format, args);
    }

    public void printError(String message) {
        err.println(message);
    }
}