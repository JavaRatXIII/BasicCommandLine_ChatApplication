/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerMain;

import java.io.BufferedReader;
import Console.*;
import Console.IConsole.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jun
 */
public class Server {
    static ServerSocket _serverSocket;
    static Socket _socket;
    static DataInputStream _inputStream;
    static DataOutputStream _outputStream;
    static String _msg;
    private ClientSocket clientSocket;
    private final IConsole _console = new Console();
    
    private List<ClientSocket> _clientConnections = new ArrayList();
    
    public Server() throws IOException
    {
        ServerSocket listener = new ServerSocket(1204);
        try 
        {
            while(true)
            {
                final Socket socket = listener.accept();
                send(socket, "Hello client");
                
                clientSocket = new ClientSocket(socket);
                _clientConnections.add(clientSocket);
                
                new Thread(() ->
                {
                    try
                    {
                        while(true)
                        {
                            getMSG(socket);
                        }
                    }
                    catch(Exception e)
                    {
                        _console.WriteLine(e.getMessage());
                    }
                }).start();
            }
        }
        finally
        {
            listener.close();
        }
    }
    
    public void getMSG(Socket socket) throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage = input.readLine();
        _console.WriteLine(clientMessage);
        
        for(ClientSocket clientConnection : _clientConnections)
        {
            if(socket != clientConnection.GetSocket())
            {
                send(clientConnection.GetSocket(), clientMessage);
            }
        }
    }
    
    private static void send(Socket socket, String message)
    {
        try
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("SERVER>> " + message);
        }
        catch(Exception e)
        {
            //handle exception here
        }
    }
}