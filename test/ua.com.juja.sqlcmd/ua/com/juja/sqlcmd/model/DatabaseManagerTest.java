package ua.com.juja.sqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by indigo on 21.08.2015.
 */
public abstract class DatabaseManagerTest {

    protected DatabaseManager manager;

    public abstract DatabaseManager getDatabaseManager();

    @Before
    public void setup() {
        manager = getDatabaseManager();
        manager.connect("article", "postgres", "postgres");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[users, messages, notes]", Arrays.toString(tableNames));
    }

    @Test
    public void testGetTableData() {
        // given
        manager.clear("users");

        // when
        DataSet input = new DataSet();

        input.put("id", 13);
        input.put("name", "Ban");
        input.put("surname", "Pogankin");
        input.put("login", "pogan");
        input.put("password", "pass111");
        input.put("email", "ban@aol.com");
        manager.create("users",input);

        DataSet input2 = new DataSet();

        input2.put("id", 1);
        input2.put("name", "John");
        input2.put("surname", "Harry");
        input2.put("login", "j_harry");
        input2.put("password", "fgd111");
        input2.put("email", "j_harry@aol.com");
        manager.create("users",input2);

        DataSet input3 = new DataSet();

        //input3.put("id", 24);
        input3.put("name", "Selena");
        input3.put("surname", "Yilams");
        input3.put("login", "selena");
        input3.put("password", "fdeeee");
        input3.put("email", "selena.y@gmail.com");
        manager.create("users",input3);


        // then
        DataSet[] users = manager.getTableData("users");
        assertEquals(3, users.length);

        DataSet users1str = users[0];
        assertEquals("[id, name, surname, login, password, email]", Arrays.toString(users1str.getNames()));
        assertEquals("[13, Ban, Pogankin, pogan, pass111, ban@aol.com]", Arrays.toString(users1str.getValues()));
        DataSet users2str = users[1];
        assertEquals("[1, John, Harry, j_harry, fgd111, j_harry@aol.com]", Arrays.toString(users2str.getValues()));

    }

    @Test
    public void testUpdateTableData() {
        // given
        manager.clear("users");

        DataSet input = new DataSet();
        input.put("id", 13);
        input.put("name", "Ban");
        input.put("surname", "Pogankin");
        input.put("login", "pogan");
        input.put("password", "pass111");
        input.put("email", "ban@aol.com");
        manager.create("users", input);

        // when
        DataSet newValue = new DataSet();
        newValue.put("password", "pass2");
        newValue.put("name", "Pup");
        manager.update("users", 13, newValue);

        // then
        DataSet[] users = manager.getTableData("users");
        assertEquals(1, users.length);

        DataSet user = users[0];
        //assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
        //assertEquals("[Pup, pass2, 13]", Arrays.toString(user.getValues()));
        assertEquals("[id, name, surname, login, password, email]", Arrays.toString(user.getNames()));
        assertEquals("[13, Pup, Pogankin, pogan, pass2, ban@aol.com]", Arrays.toString(user.getValues()));
    }
}
