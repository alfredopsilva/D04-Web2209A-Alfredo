package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

/**
 * A reader thread for reading messages from a chat server.
 */
public class ReaderThread extends Thread
{
    private final BufferedReader reader;

    /**
     * Create a reader thread for reading messages from a chat server.
     * @param socket Must not be null.
     * @throws NullPointerException If socket is null.
     */
    public ReaderThread(Socket socket) throws IOException
    {
        Objects.requireNonNull(socket);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String message = reader.readLine();
                if (message != null)
                    System.out.println(message);
                else
                    return;
            }
        }
        catch (IOException e)
        { }
    }
}
