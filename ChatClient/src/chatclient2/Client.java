/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient2;

import Interfaces.IBufferedReaderFactory;
import Interfaces.IPrintWriterFactory;
import Interfaces.ISocketFactory;
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
    private BufferedReader _input;
    
    public Client(ISocketFactory socketFactory, IPrintWriterFactory printWriterFactory, IBufferedReaderFactory bufferedReaderFactory) throws IOException
    {
        _socket = socketFactory.GetSocket();
        _input = bufferedReaderFactory.GetBufferedReader(_socket);
        
        try
        {   
            Receive();
            _outputWriter = printWriterFactory.GetPrintWriter(_socket);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void Receive() throws IOException
    {
        String serverMessage = _input.readLine();
        System.out.println(serverMessage);
    }
    
    public void Send(String name,String message)
    {
        _outputWriter.println(name + ">> " + message);
    }
}