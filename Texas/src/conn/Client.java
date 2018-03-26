package conn;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	Socket socket;
	private  ReadThread readThread;
	private  WriteThread writeThread;
	private ArrayList<IMessageObserver> observers = new ArrayList<IMessageObserver>();
	
	Client(String serverIp, int serverPort) {
		try {
			socket = new Socket(serverIp, serverPort);
			socket = new Socket(serverIp, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void init() {
		readThread = new ReadThread(this);
		readThread.start();
		writeThread= new WriteThread(this);
		writeThread.start();
	}
	
	public void registerObserver(IMessageObserver observer) {
		observers.add(observer);
	}
	
	public void unregisterObserver(IMessageObserver observer) {
		observers.remove(observer);
	}
	
	public void dispatchMessage(String msg) {
		for(IMessageObserver obs:observers) {
			obs.onMessageReceive(msg);
			System.out.println(msg);
		}
	}
	
	public IMessagePoster obtainMesaagePoster() {
		return new IMessagePoster() {
			
			@Override
			public void send(String msg) {
				writeThread.addMessage(msg);
			}
		};
	}
	
	public void finish() {
		try {
			socket.close();
			writeThread.finish();
			readThread.finish();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Client client = new Client("127.0.0.1", 10002);
		client.init();
		}
}



