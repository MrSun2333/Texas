package searchEngine;

public class RandomEvaluator implements Evaluator {

	RandomEvaluator() {
	}

	@Override
	public void evaluate() {// �Խڵ�������������շ���һ��������������ڸýڵ������һ������

	}

	// ��������Ĺ�ֵ����
	public void chanceEvaluate(int[] pokerPoints) {

	};
	
	// Ҷ�ӽڵ�Ĺ�ֵ����
	public int[] leafEvaluate(State state) {
		int value[] = new int[2];// ��λ��ҵ�����
		
		// r[i] = (n * r[i] + r[i]') / (n + 1)
		// r[i]�ǵ�iλ��ҵľ���ֵ��n�������������
		
//		int bestValue = 0;
		
		return value ;

	};

	public void decisionEvaluate() {

	};

	public void opponentEvaluate() {

	};

}
