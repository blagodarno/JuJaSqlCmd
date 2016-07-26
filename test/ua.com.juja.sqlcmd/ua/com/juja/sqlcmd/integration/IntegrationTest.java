package ua.com.juja.sqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import sun.rmi.log.LogOutputStream;
import ua.com.juja.sqlcmd.controller.Main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by asu06 on 25.07.16.
 */
public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;

    @Before
    public  void setup () {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

    }

    @Test
    public void testHelp(){
        //given
        in.add("help");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Существующие команды:\n" +
                "\tconnect|databaseName|userName|password\n" +
                "\t\tдля подключения к базе данных\n" +
                "\tlist\n" +
                "\t\tдля получения списка всех таблиц базы, к которой подключились\n" +
                "\tfind|tableName\n" +
                "\t\tдля получения содержимого таблицы 'tableName'\n" +
                "\thelp\n" +
                "\t\tдля вывода этого списка на экран\n" +
                "\texit\n" +
                "\t\tдля выхода из программы\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }


    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return  e.getMessage();
        }
    }

    @Test
    public void testExit(){
        //given
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Bye ! ! !\n", getData());
    }
    @Test
    public void testListWithoutConnect(){
        //given
        in.add("list");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Вы не можете использовать 'list' пока не подключитесь с помощью connect\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }

    @Test
    public void testFindWithoutConnect(){
        //given
        in.add("find|users");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Вы не можете использовать 'find|users' пока не подключитесь с помощью connect\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }

    @Test
    public void testUnsupported(){
        //given
        in.add("qwe");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Вы не можете использовать 'qwe' пока не подключитесь с помощью connect\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect(){
        //given
        in.add("connect|article|postgres|postgres");
        in.add("qwe");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Успех!\n" +
                "Enter command or help:\n" +
                "Illegal command : qwe\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }

    @Test
    public void testListAfterConnect(){
        //given
        in.add("connect|article|postgres|postgres");
        in.add("list");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Успех!\n" +
                "Enter command or help:\n" +
                "[users, messages, notes]\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }

    @Test
    public void testFindExistAfterConnect(){
        //given
        in.add("connect|article|postgres|postgres");
        in.add("find|users");
        in.add("exit");

        // when
        Main.main(new String [0]);

        //then
        assertEquals("Привет юзер!\n" +
                "Введи, пожалуйста имя базы данных, имя пользователя и пароль в формате: connect|database|userName|password\n" +
                "Успех!\n" +
                "Enter command or help:\n" +
                "--------------------\n" +
                "|id|name|surname|login|password|email|\n" +
                "--------------------\n" +
                "Enter command or help:\n" +
                "Bye ! ! !\n", getData());
    }


}
