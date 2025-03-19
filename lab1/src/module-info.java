/**
 * 
 */
/**
 * 
 */
module lab1 {
	requires java.desktop;
	requires java.rmi;
	exports os.chat.server to java.rmi;
	exports os.chat.client to java.rmi;
}