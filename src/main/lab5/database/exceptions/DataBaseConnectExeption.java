package main.lab5.database.exceptions;

public class DataBaseConnectExeption extends RuntimeException {
    public DataBaseConnectExeption(String message) {
        super(message);
    }
}
