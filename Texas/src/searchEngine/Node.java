package searchEngine;

import java.util.List;
import basic.Action;

public class Node extends MatchState {
	Node(int ID) {
		super(ID);
	}

	int visitCount;// ���ʴ���
	int tn;// ʱ��
	NodeType node_tpye;// �ڵ�����
	int childIndex;
	Action last_action;
	int position;
    Node parent;
    List<Node> children;
    
    // ����ӽڵ�
    public void addChild() {
    	
    }
    // ģ��
    public void simulation() {
    	
    }
    
    // ѡ���ӽڵ�
    public Node selectChild() {
		return null;
    }
    
    // ucb��ʽ
    public float ucb1() {
		return 0;
    }    
    
}
