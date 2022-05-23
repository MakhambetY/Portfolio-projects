import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import GameBase.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * NOTE: sometimes an exception may occur,
 * it may occur at any time (at the beginning or never).
 * I tried to handle this problem, but it occurs anyway
 * and I don't know the cause of it,
 * because the compiler does not show me where exactly it occurs.
 * Solution: just restart the game and may be it will work smoothly :)
 */

public class Server extends Application{

    private static String fileName;

    public static void main(String[] args) {
        if ( args.length == 0) fileName = "wotMap1.txt"; // Default map
        else fileName = args[0]; // get file name from console

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        Scanner in = new Scanner(new File(fileName));
        Game WOT2 = null;

        try {
            WOT2 = new Game(new Map(in)); // Create a map based game
        } catch (InvalidMapException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        in.close();

        Tank hostTank = new Tank("Host"); // Host tank with nickname "Host"
        WOT2.addPlayer(hostTank); // Add host to the game
        WOT2.addControl(hostTank); // Add control to the Host
        WOT2.spawnBots(); // Activate bot spawn

        // Create a scene and place game in the stage
        primaryStage.setMinHeight(WOT2.getMap().getSize()*50);
        primaryStage.setMinWidth(WOT2.getMap().getSize()*50);
        primaryStage.setScene(WOT2.getGameScene());
        primaryStage.show();

        Game finalWOT = WOT2;
        new Thread( () -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);

                // Listen for a connection request
                Socket socket = serverSocket.accept();
                Tank tank = new Tank("Client"); // Client tank with nickname "Client"
                Platform.runLater(() -> finalWOT.addPlayer(tank));
                System.out.println("Accepted");

                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                System.out.println("Streams are ready");

                // Get control information from client
                try{
                    while (tank.getHP() != 0){
                        finalWOT.actionPlayer(tank, inputFromClient.readInt());
                    }
                }
                catch (SocketException socketException){
                    System.out.println(tank.getName() + " exit"); //PlayerName exit message
                }

            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}

