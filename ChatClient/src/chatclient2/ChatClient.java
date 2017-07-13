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
        IUserNameStorageManager storageManager = new UserNameStorageManager();
        String name = " ";
        String password = " ";
        
        Client client = new Client(new SocketFactory(), new PrintWriterFactory(), new BufferedReaderFactory());
        
        console.WriteLine("Would you like to set a new username or choose a previous username? [Choose] [Set]");
        String userChoice = console.ReadLine().toUpperCase();
        
        if(userChoice.equals("SET"))
        {
            console.WriteLine("Set your username");
            name = console.ReadLine();
            
            boolean nameTaken = storageManager.CheckName(name);
            while(nameTaken==false)
            {
                console.WriteLine("That name is taken, try again");
                name = console.ReadLine();
                nameTaken = storageManager.CheckName(name);
            }
            
            console.WriteLine("Set your password");
            password = console.ReadLine();
        
            storageManager.SaveName(name, password);
        }
        else if(userChoice.equals("CHOOSE"))
        {
            console.WriteLine("State Username");
            name = console.ReadLine();
            boolean foundName = storageManager.SelectUsername(name);
            while(foundName==false)
            {
                console.WriteLine("Cannot find name, try again");
                name = console.ReadLine();
                foundName = storageManager.SelectUsername(name);
            }
            
            console.WriteLine("State Password");
            password = console.ReadLine();
            boolean loginSuccessful = storageManager.Login(password);
            while(loginSuccessful==false)
            {
                console.WriteLine("Password is incorrect, try again");
                password = console.ReadLine();
                loginSuccessful = storageManager.Login(password);
            }
        }
        else
        {
            name = "Annonymous Client";
            console.WriteLine("You are an annonymous client");
        }
        
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
