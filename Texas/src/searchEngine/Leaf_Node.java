package searchEngine;

/**
 * Ҷ�ӽ�㣬��ʾ����������Ϸ��ֹ��״̬
 * Ϊ���ַ�����ƣ���ȫ�������ڸ��ʷֲ��ķ������
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
