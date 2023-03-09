package chatserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

public class ListenerTask extends Task{
    
    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private TextArea window;
    private Socket connection;
    
    // constructor recieves references to the text area and the connection

    public ListenerTask(TextArea windowIn, Socket connectionIn) {
        window = windowIn;
        connection = connectionIn;
        
        try{
            // create input stream from the remort machine
            inputStream = connection.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
        }catch(IOException e){
            
        }
    }
    
    @Override
    protected Void call(){
        String msg;
        while(true){
            try{
                msg = dataInputStream.readUTF();
                window.appendText(msg);
            }catch(IOException e){
            }
        }
    }
    
}
