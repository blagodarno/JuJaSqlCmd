package ua.com.juja.sqlcmd.model;

import static org.junit.Assert.assertEquals;

/**
 * Created by asu06 on 13.07.16.
 */
public class JDBCDatabaseManagerTest extends DatabaseManagerTest {

    @Override
    public DatabaseManager getDatabaseManager() {
        return new JDBCDatabaseManager();
    }
}



