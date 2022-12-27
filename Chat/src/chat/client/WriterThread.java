package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * A writer thread for reading messages from the standard input stream,
 * and writing messages to a chat server.
 */
public class WriterThread extends Thread
{
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    /**
     * Create a writer thread for reading messages from
     * the standard input stream, and writing messages to a chat server.
     * @param socket Must not be null.
     * @throws NullPointerException If socket is null.
     */
    public WriterThread(Socket socket) throws IOException
    {
        this.socket = Objects.requireNonNull(socket);
        writer = new PrintWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run()
    {
        try
        {
            String username = readUsername();
            writer.println(username);
            writer.flush();

            while (true)
            {
                String message = reader.readLine();
                writer.println(message);
                writer.flush();

                if (message.equalsIgnoreCase("quit"))
                    return;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            tryCloseSocket();
        }
    }

    private String readUsername() throws IOException
    {
        while (true)
        {
            System.out.println("Enter username:");
            String username = reader.readLine();
            if (username != null && !username.isBlank())
                return username;

            System.out.println("Error: Username must not be empty.");
        }
    }

    private void tryCloseSocket()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
