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
        view.write("Hi, user!!!");
        while (true) {
            view.write("Enter please: databasename|user|password");
            String string = view.read();
            String[] data = string.split("[|]");
            String databaseName = data[0];
            String username = data[1];
            String password = data[2];
            try {
                manager.connect(databaseName, username, password);
                break;
            } catch (Exception e) {
                view.write("Fail becose: " + e.getMessage() + " " + e.getCause().getMessage());
                view.write("Try again!");
            }
        }
        view.write("Success!");
    }
}
