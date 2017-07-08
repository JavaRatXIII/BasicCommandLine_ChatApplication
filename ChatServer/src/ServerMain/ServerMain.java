package ServerMain;

import java.io.IOException;
import Console.*;
import Console.IConsole.*;
import Utilities.ClientSocketFactory;
import Utilities.ConsoleFactory;

/**
 *
 * @author Jun
 */
public class ServerMain {
    
    public static void main(String[] args) throws IOException {
        IConsole console = new Console();
        
        console.WriteLine("SERVER READY");
        Server server = new Server(new ConsoleFactory(), new ClientSocketFactory());
    }
}
