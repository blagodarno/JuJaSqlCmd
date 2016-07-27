package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 26.07.16.
 */
public class Clear implements Command {
    private final View view;
    private final DatabaseManager manager;

    public Clear(View view, DatabaseManager manager) {

        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear|");
    }

    @Override
    public void process(String command) {
      String [] data = command.split("\\|");
        if (data.length != 2){
            throw new IllegalArgumentException("Формат команды 'clear|tableName', а не " + command);
        }
        manager.clear(data[1]);
        view.write(String.format("Таблица %s была очищена.", data[1]));
    }
}
