/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Interfaces.IClientSocketFactory;
import ServerMain.ClientSocket;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public class ClientSocketFactory implements IClientSocketFactory{

    @Override
    public ClientSocket GetClientSocket(Socket socket)
    {
        return new ClientSocket(socket);
    }
}
