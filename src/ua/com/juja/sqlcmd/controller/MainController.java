package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.InMemoryDatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.sqlcmd.view.Console;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 13.07.16.
 */
public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public static void main(String[] args) {

    }

    public void  run(){
        connectToDb();
    }


    private void connectToDb() {
    view.write("Привет юзер!");
    view.write("Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: database|userName|password");

    while (true) {
        try {
            String string = view.read();
            String[] data = string.split("\\|");
            if (data.length != 3) {
                throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 3, но есть: " + data.length);
            }
            String databaseName = data[0];
            String userName = data[1];
            String password = data[2];

            manager.connect(databaseName, userName, password);
            break;
        } catch (Exception e) {
            printError(e);
        }
    }

    view.write("Успех!");
}

    private void printError(Exception e) {
        String message = /*e.getClass().getSimpleName() + ": " + */ e.getMessage();
        Throwable cause = e.getCause();
        if (cause != null) {
            message += " " + /*cause.getClass().getSimpleName() + ": " + */ cause.getMessage();
        }
        view.write("Неудача! по причине: " + message);
        view.write("Повтори попытку.");
    }

}
