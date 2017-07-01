package Console;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Console.IConsole;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jun
 */
public class Console implements IConsole
{
    private final BufferedReader _bufferedReader;
    public Console()
    {
        _bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    @Override
    public void WriteLine(String message)
    {
        System.out.println(message);
    }
    
    @Override
    public String ReadLine() throws IOException
    {
        return _bufferedReader.readLine();
    }
}
