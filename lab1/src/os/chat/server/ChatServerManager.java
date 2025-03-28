package os.chat.server;

import java.util.Vector;


//class used by the rmi
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * This class manages the available {@link ChatServer}s and available rooms.
 * <p>
 * At first you should not modify its functionalities but only export
 * them for being called by the {@link ChatClient}.
 * <p>
 * Later you will modify this to allow creating new rooms and
 * looking them up from the {@link ChatClient}.
 */
public class ChatServerManager implements ChatServerManagerInterface {

    /**
     * NOTE: technically this vector is redundant, since the room name can also
     * be retrieved from the chat server vector.
     */
	private Vector<String> chatRoomsList;
	
	private Vector<ChatServer> chatRooms;

    private static ChatServerManager instance = null;
    
    
    //define the Regitry variable used in the entry pitn 
    private Registry registry;
	
	/**
	 * Constructor of the <code>ChatServerManager</code>.
	 * <p>
	 * Must register its functionalities as stubs to be called from RMI by
	 * the {@link ChatClient}.
	 */
	public ChatServerManager () {
		
		
		//DOne , create create variable on the constructors
		//instancie les variables dans le  constructeur
		chatRooms = new Vector<ChatServer>();
		chatRoomsList = new Vector<String>();
		
		// initial: we create a single chat room and the corresponding ChatServer
		chatRooms.add(new ChatServer("sports"));
		chatRoomsList.add("sports");
		
		/*
		 * TODO register the server manager object as a "ChatServerManager" on the RMI registry
		 * so it can be called by clients.
		 */
		
		//DONE !
		
		
	    try {
	        ChatServerManagerInterface stub = (ChatServerManagerInterface) UnicastRemoteObject.exportObject(this, 0);
	        registry = LocateRegistry.getRegistry();
	        registry.rebind("ChatServerManager", stub);
	        //Je dois changer la clase de mon contract pour mettre "Extend remote" pour que ça fonctionne
	    } catch (RemoteException e) {
	        System.out.println("can not export the object");
	        e.printStackTrace();
	    }
	    System.out.println("ChatServerManager was created");


		
	}

    /**
     * Retrieves the chat server manager instance. This method creates a
     * singleton chat server manager instance if none was previously created.
     * @return a reference to the singleton chat server manager instance
     */
    public static ChatServerManager getInstance() {
	if (instance == null)
	    instance = new ChatServerManager();

	return instance;
    }

        /**
	 * Getter method for list of chat rooms.
	 * @return  a list of chat rooms
	 * @see Vector
	 */
	public Vector<String> getRoomsList() {
		return chatRoomsList;
	}

        /**
	 * Creates a chat room with a specified room name <code>roomName</code>.
	 * @param roomName the name of the chat room
	 * @return <code>true</code> if the chat room was successfully created,
	 * <code>false</code> otherwise.
	 */
	public boolean createRoom(String roomName) {
		
	
		
		/*
		 * TODO add the code to create a new room
		 */
		
		//done , /System.err.println("server manager method createRoom not implemented.");
		try {
			chatRoomsList.add(roomName);
		} catch (Exception e) {
			return false; /* Should catch all exceptions... */
		}
		return true;
		
	
	}	
	
	// I create an entry point for my server
	public static void main(String[] args) {
		/* François à ajouter cette partie pour créer le registry */
		try {
			LocateRegistry.createRegistry(1099);
			/* port 1099 is the default port for RMI */
			
		} catch (RemoteException e) {
			System.out.println("error_can_not_create_registry");
			e.printStackTrace(); return;
		}
		System.out.println("registry_was_created");
		getInstance();
		/* François à ajouter cette partie pour créer le ChatServerManager */
	}
	
}
