package searchEngine;

public class RandomEvaluator implements Evaluator {

	RandomEvaluator() {
	}

	@Override
	public void evaluate() {// 对节点进行评估，最终返回一个保存所有玩家在该节点收益的一个数组

	}

	// 对随机结点的估值函数
	public void chanceEvaluate(int[] pokerPoints) {

	};
	
	// 叶子节点的估值函数
	public int[] leafEvaluate(State state) {
		int value[] = new int[2];// 两位玩家的收益
		
		// r[i] = (n * r[i] + r[i]') / (n + 1)
		// r[i]是第i位玩家的经验值，n代表玩家数量，
		
//		int bestValue = 0;
		
		return value ;

	};

	public void decisionEvaluate() {

	};

	public void opponentEvaluate() {

	};

}
