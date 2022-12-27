package chat.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

/**
 * A chat client for connecting to a chat server,
 * reading messages from the standard input stream,
 * writing messages to the chat server, and reading messages
 * sent from the chat server.
 */
public class Client
{
    private final String host;
    private final int port;

    /**
     * Create a chat client for connecting to a chat server,
     * reading messages from the standard input stream,
     * writing messages to the chat server, and reading messages
     * sent from the chat server.
     * @param host Server host name or address. Must not be null.
     * @param port Server port number.
     * @throws NullPointerException If host is null.
     */
    public Client(String host, int port)
    {
        this.host = Objects.requireNonNull(host);
        this.port = port;
    }

    public void start()
    {
        try
        {
            Socket socket = new Socket(host, port);

            WriterThread writer = new WriterThread(socket);
            ReaderThread reader = new ReaderThread(socket);

            writer.start();
            reader.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
