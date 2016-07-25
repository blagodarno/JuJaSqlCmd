package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 25.07.16.
 */
public class Connect implements Command {
    private View view;
    private DatabaseManager manager;

    public Connect(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {

                try {
                    String[] data = command.split("\\|");
                    if (data.length != 4) {
                        throw new IllegalArgumentException("Неверно количество параметров разделенных знаком '|', ожидается 4, но есть: " + data.length);
                    }
                    String databaseName = data[1];
                    String userName = data[2];
                    String password = data[3];

                    manager.connect(databaseName, userName, password);
                    view.write("Успех!");
                } catch (Exception e) {
                    printError(e);
                }

        }

        private void printError(Exception e) {
            String message =  e.getMessage();
            Throwable cause = e.getCause();
            if (cause != null) {
                message += " " + cause.getMessage();
            }
            view.write("Неудача! по причине: " + message);
            view.write("Повтори попытку.");
        }

    }

