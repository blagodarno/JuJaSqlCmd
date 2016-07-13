package ua.com.juja.sqlcmd.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by asu06 on 13.07.16.
 */
public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {

    @Override
    public DatabaseManager getDatabaseManager() {
        return new InMemoryDatabaseManager();
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[users]", Arrays.toString(tableNames));
    }


}


