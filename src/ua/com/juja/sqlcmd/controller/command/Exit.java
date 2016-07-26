package ua.com.juja.sqlcmd.controller.command;

import com.sun.javaws.exceptions.ExitException;
import ua.com.juja.sqlcmd.view.View;

/**
 * Created by asu06 on 22.07.16.
 */
public class Exit implements Command {

    private  View view;

    public Exit (View view){
        this.view=view;
    }
    @Override
    public boolean canProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) {
        view.write("Bye ! ! !");
        throw new Exitexeption();

    }
}
