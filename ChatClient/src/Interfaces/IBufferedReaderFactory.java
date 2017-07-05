/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.io.BufferedReader;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public interface IBufferedReaderFactory 
{
    BufferedReader GetBufferedReader(Socket socket);
}
