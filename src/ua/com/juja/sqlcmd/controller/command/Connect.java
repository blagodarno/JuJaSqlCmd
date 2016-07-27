package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 25.07.16.
 */
public class Connect implements Command {
    private static String COMMAND_SAMPLE = "connect|article|postgres|postgres";
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

                    String[] data = command.split("\\|");
                    if (data.length != parametersLength()) {
                        throw new IllegalArgumentException(
                                String.format("Неверно количество параметров разделенных знаком '|', " +
                                        "ожидается '%s', но есть %s: ",
                                        parametersLength(), data.length));
                    }
                    String databaseName = data[1];
                    String userName = data[2];
                    String password = data[3];

                    manager.connect(databaseName, userName, password);
                    view.write("Успех!");
        }

    private int parametersLength() {
        return COMMAND_SAMPLE.split("\\|").length;
    }

    }

