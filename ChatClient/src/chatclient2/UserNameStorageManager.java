package chatclient2;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Jun
 */
public class UserNameStorageManager {
    private ResultSet _resultSet = null;
    private Connection _connection = null;
    private Statement _statement = null;
    private ArrayList<String> userNames = new ArrayList<String>();
    
    private ResultSet getSqlQueryResult(String sqlStatement) throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        _connection = DriverManager.getConnection("jdbc:sqlite:UserNames.db");
        _connection.setAutoCommit(false);

        _statement = _connection.createStatement();
        _resultSet = _statement.executeQuery(sqlStatement);
        
        return _resultSet;
    }
    
    private void executeSqlUpdate(String sqlStatement) throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        _connection = DriverManager.getConnection("jdbc:sqlite:UserNames.db");
        _connection.setAutoCommit(false);
        
        _statement = _connection.createStatement();
        
        _statement.executeUpdate(sqlStatement);
        _connection.commit();
        _statement.close();
        _connection.close();
    }
    
    private void closeDatabase() throws SQLException
    {
        _resultSet.close();
        _statement.close();
        _connection.close();
    }
}
