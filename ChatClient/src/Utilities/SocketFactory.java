/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Interfaces.ISocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jun
 */
public class SocketFactory implements ISocketFactory
{
    @Override
    public Socket GetSocket() 
    {
        try 
        {
            return new Socket("192.168.1.110", 1204);
        } 
        catch (IOException ex) 
        {
            return null;
        }
    }
}
