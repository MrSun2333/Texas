package conn;

import java.io.*;
import java.util.*;

public class WriteThread extends Thread {
	private PrintWriter writer;
	private Queue<String> messages;
	private boolean live = true;

	public WriteThread(Client client) {
		messages = new LinkedList<String>();
		try {
			writer = new PrintWriter(client.socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMessage(String msg) {
		messages.offer(msg);
		synchronized (this) {
			this.notify();
		}
	}

	public void finish() {
		live = false;
		synchronized (this) {
			this.notify();
		}
	}

	@Override
	public void run() {
		while (live) {
			System.out.println("写入进程启动");
			String msg = null;
			while ((msg = messages.poll()) != null) {
				writer.print(msg);
				writer.flush();
			}
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		writer.close();
		System.out.println("写入进程结束");
	}
}
