package os.chat.server;

import java.util.Vector;

import os.chat.client.CommandsFromServer;



import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * Each instance of this class is a server for one room.
 * <p>
 * At first there is only one room server, and the names of the room available
 * is fixed.
 * <p>
 * Later you will have multiple room server, each managed by its own
 * <code>ChatServer</code>. A {@link ChatServerManager} will then be responsible
 * for creating and adding new rooms.
 */
public class ChatServer implements ChatServerInterface {
	
	private String roomName;
	private Vector<CommandsFromServer> registeredClients;
	
  /**
   * Constructs and initializes the chat room before registering it to the RMI
   * registry.
   * @param roomName the name of the chat room
   */
	public ChatServer(String roomName){
		this.roomName = roomName;
		registeredClients = new Vector<CommandsFromServer>();
		
		/*
		 * TODO register the ChatServer to the RMI registry
		 */
		
		

	    try {
	    	
	    	//je doit inclure les classe RMI 
	        Registry registry = LocateRegistry.getRegistry();
	        ChatServerInterface stub = (ChatServerInterface) UnicastRemoteObject.exportObject(this, 0);
	        //pour que ça soit rebin,d, je dois ajouter extejd Remeote
	        registry.rebind("room_" + roomName, stub);
	    } catch (RemoteException e) {
	        System.out.println("Failed to create chat room: " + roomName);
	        e.printStackTrace();
	    }
	//Done

	}

	/**
	 * Publishes to all subscribed clients (i.e. all clients registered to a
	 * chat room) a message send from a client.
	 * @param message the message to propagate
	 * @param publisher the client from which the message originates
	 */	
	public void publish(String message, String publisher) {
		
		
		/*
		 * TODO send the message to all registered clients
		 */
		
	    for (CommandsFromServer client : registeredClients) {
	        try {
	            client.receiveMsg(roomName, publisher + ": " + message);
	            //Remote Exceptoin impique que ServerINterface th
	        } catch (RemoteException e) {
	            System.out.println("Client disconnected. Removing from room: " + roomName);
	            registeredClients.remove(client);
	        }
	    }
	    System.out.println("[server] publishing '" + message + "' from '" + publisher + "'");
	    
	    //done ,System.err.println("TODO: publish is not implemented");
		

	}

	/**
	 * Registers a new client to the chat room.
	 * @param client the name of the client as registered with the RMI
	 * registry
	 */
	public void register(CommandsFromServer client)throws RemoteException {
		
		
		
		/*
		 * TODO register the client
		 */
		
		 registeredClients.add(client);
		 System.out.println("A client has joined the room: " + roomName);
		 
		 //Done , System.err.println("TODO: register is not implemented");
	}

	/**
	 * Unregisters a client from the chat room.
	 * @param client the name of the client as registered with the RMI
	 * registry
	 */
	public void unregister(CommandsFromServer client) throws RemoteException {
		
		
		
		/*
		 * TODO unregister the client
		 */
	    registeredClients.remove(client);
	    System.out.println("A client has left the room: " + roomName);

		
		//Done , System.err.println("TODO: unregister is not implemented");
	}
	
}
