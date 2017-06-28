/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmultiuser;

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
    static IConsole console = new Console();
    
    public Server() throws IOException
    {
        String input2 = "";
        ServerSocket listener = new ServerSocket(1204);
        try {
            while(true) {
                Socket socket = listener.accept();
                try {
                    input2 = console.ReadLine();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("SERVER>> " + input2);
                    
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String clientMessage = input.readLine();
                    System.out.println(clientMessage);
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }

    }
    
    public  String getMSG()
    {
        String msg = _msg;
        return msg;
    }
    
    public static void send()
    {
        try
        {
            String messageOut = "";
            messageOut = console.ReadLine();
            _outputStream.writeUTF(messageOut);
        }
        catch(Exception e)
        {
            //handle exception here
        }
    }
}
