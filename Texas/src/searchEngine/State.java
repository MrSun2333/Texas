package searchEngine;

import basic.Action;
import basic.Phase;

/**
 * ��ʾ����״̬����Ϣ
 * @author Plonk
 *
 */
public class State {
	protected int ID;// ��Ϸ���
	protected int maxJettons;// Ŀǰ������ע��������
	protected int[] input_jettons;// ������Ϸ��ÿλ�����ʳ���Ͷע�ĳ������� 
	protected Action action[][];// ÿ���׶���ҵ���ע��Ϊ����
	
	protected Phase phase;// ��ǰ��Ϸ�׶�
	protected boolean finished;// ��Ϸ�Ƿ����
	protected int boardCards[];// ���Ϲ���������
	protected int holdCards[];// ÿ��������еĵ���
	
	State(int ID) {
		this.ID = ID;
		phase = Phase.NULL;
		input_jettons = new int[2];// ���˲���
		boardCards= new int[5];
		holdCards = new int[2];
	}
}
