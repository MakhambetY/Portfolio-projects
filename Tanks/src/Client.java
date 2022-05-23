
import GameBase.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;


public class Client extends Application{

    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    private static String fileName;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new Pane(); // Empty pane

        // Create a scene and place "Control Panel" in the stage
        Scene scene = new Scene(pane, 450, 200);
        primaryStage.setTitle("Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // Send controller to server via WASD
        scene.setOnKeyPressed(keyEvent -> {
            try{
                switch (keyEvent.getCode()){
                    case W -> toServer.writeInt(1);
                    case S -> toServer.writeInt(2);
                    case A -> toServer.writeInt(3);
                    case D -> toServer.writeInt(4);
                    case SPACE -> toServer.writeInt(5);
                }
                toServer.flush();
            }catch (IOException ex) {
                System.err.println(ex);
            }
        });

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}

