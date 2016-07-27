package ua.com.juja.sqlcmd.controller;

import ua.com.juja.sqlcmd.controller.command.*;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 13.07.16.
 */
public class MainController {

    private Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[] {
                new Connect (view, manager),
                new Help(view),
                new Exit(view),
                new IsConnected (view, manager),
                new List (view, manager),
                new Clear (view, manager),
                new Create (view, manager),
                new Find(view, manager),
                new Unsupported(view)};
    }

    public static void main(String[] args) {

    }

    public void  run() {
        view.write("Привет юзер!");
        view.write("Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password");

        try {
            doWork();
        }catch (Exitexeption e){
            //do nothing
        }
        }

    private void doWork() {
        while (true) {
            String input = view.read();
            if (input == null) { //null catcher if close application
                new Exit(view).process(input);
            }
            for (Command command : commands) {
                if (command.canProcess(input)) {
                    command.process(input);
                    break;
                }
            }
            view.write("Enter command or help:");
        }
    }

}
