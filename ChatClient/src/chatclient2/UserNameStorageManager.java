package chatclient2;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Jun
 */
public class UserNameStorageManager implements IUserNameStorageManager
{
    private ResultSet _resultSet = null;
    private Connection _connection = null;
    private Statement _statement = null;
    private ArrayList<String> _userNames = new ArrayList<String>();
    private ArrayList<String> _userPasswords = new ArrayList<String>();
    private int _position = 0;
    
    public UserNameStorageManager()
    {
        initialiseLists();
    }
    
    @Override
    public ResultSet getSqlQueryResult(String sqlStatement)
    {
        try
        {
            initialiseConnection();

            _statement = _connection.createStatement();
            _resultSet = _statement.executeQuery(sqlStatement);
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        
        return _resultSet;
    }
    
    @Override
    public void executeSqlUpdate(String sqlStatement)
    {
        try
        {
            initialiseConnection();

            _statement = _connection.createStatement();

            _statement.executeUpdate(sqlStatement);
            _connection.commit();
            _statement.close();
            _connection.close();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public void closeDatabase()
    {
        try
        {
            _resultSet.close();
            _statement.close();
            _connection.close();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public void initialiseLists()
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
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public void SaveName(String name, String password)
    {
        String sql = "INSERT INTO Usernames(Username,Password) VALUES('"+name+"','"+password+"')";
        _userNames.add(name);
        _userPasswords.add(password);
        executeSqlUpdate(sql);
    }
    
    @Override
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
    
    @Override
    public boolean SelectUsername(String name)
    {
        for(int i = 0; i < _userNames.size(); i++)
        {
            if(_userNames.get(i).equals(name))
            {
                _position = i;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean Login(String password)
    {
        return _userPasswords.get(_position).equals(password);
    }
    
    private void initialiseConnection() throws ClassNotFoundException, SQLException
    {
        
        Class.forName("org.sqlite.JDBC");
        _connection = DriverManager.getConnection("jdbc:sqlite:rsc\\UserNames.db");
        _connection.setAutoCommit(false);
    }
}
