package chatclient2;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Jun
 */
public class UserNameStorageManager 
{
    private ResultSet _resultSet = null;
    private Connection _connection = null;
    private Statement _statement = null;
    private ArrayList<String> _userNames = new ArrayList<String>();
    private ArrayList<String> _userPasswords = new ArrayList<String>();
    
    public UserNameStorageManager()
    {
        initialiseLists();
    }
    
    private ResultSet getSqlQueryResult(String sqlStatement) throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        _connection = DriverManager.getConnection("jdbc:sqlite:rsc\\UserNames.db");
        _connection.setAutoCommit(false);

        _statement = _connection.createStatement();
        _resultSet = _statement.executeQuery(sqlStatement);
        
        return _resultSet;
    }
    
    private void executeSqlUpdate(String sqlStatement) throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        _connection = DriverManager.getConnection("jdbc:sqlite:rsc\\UserNames.db");
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
    
    private void initialiseLists()
    {
        String sql = "SELECT Username, Password FROM Usernames";
        try
        {
            ResultSet rs = getSqlQueryResult(sql);
            while(rs.next())
            {
                _userNames.add(rs.getString("Username"));
                _userPasswords.add(rs.getString("Password"));
            }
            closeDatabase();
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }
    
    public void SaveName(String name, String password)
    {
        String sql = "INSERT INTO Usernames(Username,Password) VALUES('"+name+"','"+password+"')";
        try
        {
            _userNames.add(name);
            _userPasswords.add(password);
            executeSqlUpdate(sql);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
    }
    
    public boolean CheckName(String name)
    {
        for(int i = 0; i < _userNames.size(); i++)
        {
            if(_userNames.get(i).equals(name))
            {
                return false;
            }
        }
        return true;
    }
}
