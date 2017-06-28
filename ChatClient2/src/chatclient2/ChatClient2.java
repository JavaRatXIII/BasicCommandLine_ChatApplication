/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import Console.*;
import Console.IConsole.*;
import java.io.IOException;
import javax.swing.JOptionPane;

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
        System.out.println("CLIENT READY");
        Client client = new Client();
        String input = console.ReadLine();
        while(!(input.equals("exit")||input.equals("Exit")))
        {
            client.Send(input);
            input = console.ReadLine();
        }
         
    }
    
}
