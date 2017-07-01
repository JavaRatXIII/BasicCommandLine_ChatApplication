/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import Console.*;
import Console.IConsole.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jun
 */
public class ChatClient2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        IConsole console = new Console();
        console.WriteLine("CLIENT READY");
        
        Client client = new Client();
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
            client.Send(input);
            input = console.ReadLine();
        }
        
        thread.stop();
    }
}
