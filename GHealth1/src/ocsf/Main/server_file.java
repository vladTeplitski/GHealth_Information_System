package ocsf.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class server_file extends Thread {
	
		private ServerSocket ss;
		
		/**
		 * server_file method he has a port of his own
		 * @param port of the server 
		 */
		public server_file(int port) {
			try {
				ss = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * run method is saving the file 
		 */
		public void run() {
			while (true) {
				try {
					Socket clientSock = ss.accept();
					saveFile(clientSock);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
/**
 * accept file & deciphering 
 * @param clientSock
 * @throws IOException
 */
		private void saveFile(Socket clientSock) throws IOException {

			FilePermission perm = new FilePermission("*", "write");
			DataInputStream dis = new DataInputStream(clientSock.getInputStream());
			FileOutputStream fos = new FileOutputStream("testfile.jpg");
			
			byte[] buffer = new byte[4096];
			
			int filesize = 15123; // Send file size in separate msg
			int read = 0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
				totalRead += read;
				remaining -= read;
				System.out.println("read " + totalRead + " bytes.");
				fos.write(buffer, 0, read);

			}
			
			fos.close();
			dis.close();
			
			
		}
		
	//	public static void main(String[] args) {
		//	server_file fs = new server_file(5553);
		//	fs.start();
		//}

	}