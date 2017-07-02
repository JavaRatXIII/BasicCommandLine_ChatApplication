package ServerMain;

import java.io.IOException;
import Console.*;
import Console.IConsole.*;

/**
 *
 * @author Jun
 */
public class ServerMain {
    
    public static void main(String[] args) throws IOException {
        IConsole console = new Console();
    
        /*console.WriteLine("Hello World!");
        String input = console.ReadLine();
        console.WriteLine("You entered: " + input);

        input = console.ReadLine();
        console.WriteLine("You entered: " + input);

        input = console.ReadLine();
        console.WriteLine("You entered: " + input);*/
        
        console.WriteLine("SERVER READY");
        Server server = new Server();
    }
}
