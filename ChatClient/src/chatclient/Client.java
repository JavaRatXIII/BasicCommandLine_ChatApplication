/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.BufferedReader;
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
    private Socket _socket;
    
    public Client() throws IOException
    {
        _socket = new Socket("192.168.1.110", 1204);
        
        try
        {   
            Receive();
            _outputWriter = new PrintWriter(_socket.getOutputStream(), true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Receive() throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        String serverMessage = input.readLine();
        System.out.println(serverMessage);
    }
    
    public void Send(String message)
    {
        _outputWriter.println("Client>> " + message);
    }
}