/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import Console.*;
import Console.IConsole.*;
import Utilities.BufferedReaderFactory;
import Utilities.PrintWriterFactory;
import Utilities.SocketFactory;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jun
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        IConsole console = new Console();
        console.WriteLine("CLIENT READY");
        
        Client client = new Client(new SocketFactory(), new PrintWriterFactory(), new BufferedReaderFactory());
        UserNameStorageManager storageManager = new UserNameStorageManager();
        
        console.WriteLine("Would you like to set a new username or choose a previous username? [Choose] [Set]");
        String userChoice = console.ReadLine().toUpperCase();
        String name = " ";
        String password = " ";
        if(userChoice.equals("SET"))
        {
            console.WriteLine("Set your username");
            name = console.ReadLine();
            console.WriteLine("Set your password");
            password = console.ReadLine();
        
            storageManager.SaveName(name, password);
        }
        
        
        
        /*boolean nameTaken = CheckName(name);
        while(nameTaken==false)
        {
            name = console.ReadLine();
            nameTaken = CheckName(userNames,name);
        }*/
        
        console.WriteLine("Message: ");
        String input = console.ReadLine();
        
        
        Thread thread = new Thread(() ->
        {
            try
            {
                while(true)
                {
                    client.Receive();
                }
            }
            catch (IOException ex)
            {
                
            }
        });
        
        thread.start();
        
        while(!(input.equals("exit")||input.equals("Exit")))
        {
            client.Send(name,input);
            input = console.ReadLine();
        }
        
        thread.stop();
    }
}
