package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * Create a thread for reading messages from a single chat client user
 * and sending messages back to the chat client.
 */
public class UserThread extends Thread
{
	private final IServer server;
	private final Socket socket;
	private final BufferedReader reader;
	private final PrintWriter writer;

	/**
	 * Create a thread for reading messages from a single chat client user
	 * and sending messages back to the chat client.
	 * @param server Must not be null.
	 * @param socket Client user socket. Must not be null.
	 * @throws NullPointerException If server or socket is null.
	 */
	public UserThread(IServer server, Socket socket) throws IOException
	{
		this.server = Objects.requireNonNull(server);
		this.socket = Objects.requireNonNull(socket);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(socket.getOutputStream(), true);
	}

	public void sendMessage(String message)
	{
		writer.println(message);
		writer.flush();
	}

	@Override
	public void run()
	{
		String username = null;
		try
		{
			username = reader.readLine();

			if (username != null)
			{
				username = addUniqueUser(username);
				processMessages(username);
			}
		}
		catch (IOException e)
		{ }
		finally
		{
			server.removeUser(username, this);
			closeSocket();
		}
	}

	private String addUniqueUser(String username)
	{
		synchronized (server)
		{
			username = server.getUniqueUsername(username);
			server.addUser(username, this);
			return username;
		}
	}

	private void processMessages(String username) throws IOException
	{
		while (true)
		{
			String message = reader.readLine();
			if (message == null || message.equalsIgnoreCase("quit"))
				return;

			server.broadcast("[" + username + "]: " + message);
		}
	}

	private void closeSocket()
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