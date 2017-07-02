/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerMain;

import java.net.Socket;

/**
 *
 * @author Jun
 */
public class ClientSocket {
    private Socket _communicationSocket;
    
    public Socket GetSocket()
    {
        return _communicationSocket;
    }
    
    public ClientSocket(Socket socket)
    {
        _communicationSocket = socket;
    }
}
