package ua.com.juja.sqlcmd.controller.command;

import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 26.07.16.
 */
public class Create implements Command {
    private final View view;
    private final DatabaseManager manager;

    public Create(View view, DatabaseManager manager) {

        this.view = view;
        this.manager = manager;
    }

    @Override
    public boolean canProcess(String command) {

        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String [] data = command.split("\\|");
        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Должно быть чётное кол-во параметров в формате: create|tableName|column1|value1|value2|....|columnN|valueN в не " + command);
        }
        String tableName = data [1];
        DataSet dataSet = new DataSet();
        for (int i = 1; i < (data.length/2); i++) {
            String columnName = data [i*2];
            String value = data [i*2 +1];
            dataSet.put(columnName,value);
        }
        manager.create(tableName, dataSet);
        view.write(String.format("Запсиь %s была успешно создана в таблице '%s'", dataSet, tableName));
    }
}
