/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Console.Console;
import Interfaces.IConsoleFactory;

/**
 *
 * @author Jun
 */
public class ConsoleFactory implements IConsoleFactory{

    @Override
    public Console GetConsole() {
        return new Console();
    }
    
}
