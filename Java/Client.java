import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

  // Do not change the final variables
  private static final int BUFFER_LENGTH = 4096;
  private final int        serverPort    = 2451;
  private BufferedReader   inputBuffer;
  private PrintWriter      output;

  private Socket udpSocket;

  private InetAddress serverIP;

  public Client() throws Exception {

    for (int a = 1; a <= 5; a++){
      if (establishConnection()){
        System.out.println("Connection has been made!");
        break;
      }
      System.out.println("Connection has failed!");
    }

    if (udpSocket == null){
      System.out.println("Connection was unable to be created!");
      throw new Exception();

    }

    output.write("Hello");
    output.flush();

    System.out.println(udpSocket.getLocalAddress() + " " + udpSocket.getLocalPort());

    int k = 1_000_000;

    while (k > 0){
      k--;
    }

    output.write("Cool!");
    output.flush();

  }

  private boolean establishConnection() {
    try{
      udpSocket = new Socket(InetAddress.getByName("127.0.0.1"), serverPort);
      output = new PrintWriter(udpSocket.getOutputStream(), true);
      inputBuffer = new BufferedReader(new InputStreamReader(udpSocket.getInputStream()));
      udpSocket.setReuseAddress(true);
      udpSocket.setSendBufferSize(BUFFER_LENGTH);
      udpSocket.setReceiveBufferSize(BUFFER_LENGTH);

      output.write("Message");
      output.flush();
    }catch (IOException e){
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
