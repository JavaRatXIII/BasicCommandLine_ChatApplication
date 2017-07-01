package Console;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jun
 */
import java.io.IOException;

public interface IConsole
{
    void WriteLine(String message);
    
    String ReadLine() throws IOException;
}
