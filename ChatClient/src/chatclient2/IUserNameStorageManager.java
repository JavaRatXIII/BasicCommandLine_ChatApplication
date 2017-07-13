/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jun
 */
public interface IUserNameStorageManager {
    ResultSet getSqlQueryResult(String sqlStatement);
    
    void executeSqlUpdate(String sqlStatement);
    
    void closeDatabase();
    
    void initialiseLists();
    
    public void SaveName(String name, String password);
    
    public boolean CheckName(String name);
    
    public boolean SelectUsername(String name);
    
    public boolean Login(String password);
}
