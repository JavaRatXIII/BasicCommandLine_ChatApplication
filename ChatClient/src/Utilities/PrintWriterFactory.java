/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Interfaces.IPrintWriterFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Jun
 */
public class PrintWriterFactory implements IPrintWriterFactory
{
    public PrintWriter GetPrintWriter(Socket socket) {
        try {
            return new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            return null;
        }
    }
}
