import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xingdai on 5/7/17.
 */
public class EchoServerWithExecutor {

  public static void main(String[] args) throws IOException {
    Executor executor = Executors.newFixedThreadPool(2);
    ServerSocket serverSocket = new ServerSocket(4567);
    while (true) {
      Socket socket = serverSocket.accept();
      SocketHandlerWithExecutor handler = new SocketHandlerWithExecutor(socket);
      executor.execute(handler);
    }
  }
}

class SocketHandlerWithExecutor extends Thread {
  private final Socket socket;
  private final OutputStream out;
  private final InputStream in;
  public SocketHandlerWithExecutor(Socket socket) throws IOException {
    this.socket = socket;
    out = socket.getOutputStream();
    in = socket.getInputStream();
  }

  @Override
  public void run() {
    try {
      int size = 1000;
      int n = 0;
      byte[] buff = new byte[size];
      while((n = in.read(buff, 0 , size)) != -1) {
        System.out.printf("Receive %d bytes.", n);
        if (new String(buff).startsWith("q")) {
          return;
        }
        out.write(buff, 0, n);
        out.flush();
      }
    } catch (IOException e) {
    } finally {
      try {
        in.close();
        out.close();
        socket.close();
      } catch (IOException e) {

      }
    }
  }
}
