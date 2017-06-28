/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public class Client {
    private PrintWriter _outputWriter;
    
    public Client() throws IOException
    {
        Socket socket = new Socket("192.168.1.110", 1204);
        
        try
        {   
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverMessage = input.readLine();
            System.out.println(serverMessage);
            
            _outputWriter = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Send(String message)
    {
        _outputWriter.println("Client>> " + message);
    }
}