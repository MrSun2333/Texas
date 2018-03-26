package conn;

import java.io.*;

public class ReadThread extends Thread {
	BufferedReader reader;
	private static final int TIMEOUT = 10000;// 连接超时
	private Client client;
	private long dutyTime;// 停延时间
	private boolean live = true;

	public ReadThread(Client client) {
		this.client = client;
		dutyTime = System.currentTimeMillis();
	}

	public void finish() {
		live = false;
	}

	@Override
	public void run() {
		System.out.println("读取进程启动");
		try {
			reader = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
			String line;
			while (live) {
				if ((line = reader.readLine()) != null) {
					dutyTime = System.currentTimeMillis();
					client.dispatchMessage(line);
				}
				if (System.currentTimeMillis() - dutyTime > TIMEOUT) {
					client.dispatchMessage("Server time out");
					client.finish();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("读取进程结束");
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}