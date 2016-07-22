package ua.com.juja.sqlcmd.controller.command;

/**
 * Created by asu06 on 22.07.16.
 */
public interface Command {

    boolean canProcess (String command);

    void process (String command);

}
