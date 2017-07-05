/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Interfaces.IBufferedReaderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public class BufferedReaderFactory implements IBufferedReaderFactory
{
    @Override
    public BufferedReader GetBufferedReader(Socket socket) 
    {
        try 
        {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (IOException ex) 
        {
            return null;
        }
    }
    
}
