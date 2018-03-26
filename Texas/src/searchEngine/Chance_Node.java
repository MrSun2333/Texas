package searchEngine;

import basic.Color;
import basic.Poker;
import basic.Phase;

/**
 * 基于德州扑克博弈的不确定性，创建了随机结点 表示在游戏中的发牌过程
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
			count = 0;// 发两张底牌，也即是从服务器端获取底牌信息
			getPrefolp();
		} else if (Phase.params(str) == Phase.FLOP) {
			count = 1;// do nothing
			getFlop();
		}

		else if (Phase.params(str) == Phase.TURN) {
			count = 2;// 发一张公共牌,也即是从服务器获取第三张公共牌
			getTurn();
		}

		else if (Phase.params(str) == Phase.RIVER) {
			count = 3;// 发两张公共牌，也即是从服务器获取第四第五张公共牌
			getRiver();
		}
 
		else {
			count = 4;// do nothing
			System.out.println(count);
		}

	}

	// 获得翻牌前信息
	private static void getPrefolp() {
		int[] pokerPoints = new int[7];
		
		// 底牌
		Poker poker1 = new Poker(Color.NULL, 0);
		Poker poker2 = new Poker(Color.NULL, 0);
		
		// 公共牌1
		Poker poker3 = new Poker(Color.NULL, 0);
		Poker poker4 = new Poker(Color.NULL, 0);
		Poker poker5 = new Poker(Color.NULL, 0);
		
		// 公共牌2
		Poker poker6 = new Poker(Color.NULL, 0);
		
		// 公共牌3
		Poker poker7 = new Poker(Color.NULL, 0);
		
		poker1.getPoint();
		
		re.chanceEvaluate(pokerPoints);
	}

	// 获得翻牌圈信息
	private static void getFlop() {
		
	}

	// 获得转牌圈信息
	private static void getTurn() {
		
	}

	// 获得河牌圈信息
	private static void getRiver() {

	}
}
