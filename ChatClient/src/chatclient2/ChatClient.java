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
