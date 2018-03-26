package searchEngine;

import basic.Color;
import basic.Poker;
import basic.Phase;

/**
 * ���ڵ����˿˲��ĵĲ�ȷ���ԣ������������� ��ʾ����Ϸ�еķ��ƹ���
 * 
 * @author Plonk
 *
 */
public class Chance_Node {
	
	
	private static RandomEvaluator re;
	
	
	
	
	
	public Chance_Node(String str) {
		re = new RandomEvaluator();
		
		int count;
		if (Phase.params(str) == Phase.PREFLOP) {
			count = 0;// �����ŵ��ƣ�Ҳ���Ǵӷ������˻�ȡ������Ϣ
			getPrefolp();
		} else if (Phase.params(str) == Phase.FLOP) {
			count = 1;// do nothing
			getFlop();
		}

		else if (Phase.params(str) == Phase.TURN) {
			count = 2;// ��һ�Ź�����,Ҳ���Ǵӷ�������ȡ�����Ź�����
			getTurn();
		}

		else if (Phase.params(str) == Phase.RIVER) {
			count = 3;// �����Ź����ƣ�Ҳ���Ǵӷ�������ȡ���ĵ����Ź�����
			getRiver();
		}
 
		else {
			count = 4;// do nothing
			System.out.println(count);
		}

	}

	// ��÷���ǰ��Ϣ
	private static void getPrefolp() {
		int[] pokerPoints = new int[7];
		
		// ����
		Poker poker1 = new Poker(Color.NULL, 0);
		Poker poker2 = new Poker(Color.NULL, 0);
		
		// ������1
		Poker poker3 = new Poker(Color.NULL, 0);
		Poker poker4 = new Poker(Color.NULL, 0);
		Poker poker5 = new Poker(Color.NULL, 0);
		
		// ������2
		Poker poker6 = new Poker(Color.NULL, 0);
		
		// ������3
		Poker poker7 = new Poker(Color.NULL, 0);
		
		poker1.getPoint();
		
		re.chanceEvaluate(pokerPoints);
	}

	// ��÷���Ȧ��Ϣ
	private static void getFlop() {
		
	}

	// ���ת��Ȧ��Ϣ
	private static void getTurn() {
		
	}

	// ��ú���Ȧ��Ϣ
	private static void getRiver() {

	}
}
