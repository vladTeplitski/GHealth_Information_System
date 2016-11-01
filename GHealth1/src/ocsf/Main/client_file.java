package ocsf.Main;

	import java.io.DataOutputStream;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.net.Socket;

	public class client_file {
		
		private Socket s;
		
		/**
		 * client_file method send file to server
		 * @param host its an address of the server
		 * @param port of the server
		 * @param file that we want to send
		 */
		public client_file(String host, int port, String file) {
			try {
				s = new Socket(host, port);
				sendFile(file);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		/**
		 * this method sending file to the server according to size
		 * @param file the file that we want to send
		 * @throws IOException
		 */
		public void sendFile(String file) throws IOException {
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			
			while (fis.read(buffer) > 0) {
				dos.write(buffer);
			}
			
			fis.close();
			dos.close();	
		}

	}
	
	
