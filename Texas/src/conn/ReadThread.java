package conn;

import java.io.*;

public class ReadThread extends Thread {
	BufferedReader reader;
	private static final int TIMEOUT = 10000;// ���ӳ�ʱ
	private Client client;
	private long dutyTime;// ͣ��ʱ��
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
		System.out.println("��ȡ��������");
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
			System.out.println("��ȡ���̽���");
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}