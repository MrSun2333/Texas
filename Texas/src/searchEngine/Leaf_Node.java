package searchEngine;

/**
 * 叶子结点，表示博弈树中游戏终止的状态
 * 为对手分配底牌，完全随机或基于概率分布的分配策略
 * @author Plonk
 *
 */
public class Leaf_Node {
	
	private RandomEvaluator re;
	
	Leaf_Node(State state) {
		re = new RandomEvaluator();
		re.leafEvaluate(state);
	}
	
}
