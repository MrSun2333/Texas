package searchEngine;

import java.util.List;
import basic.Action;

public class Node extends MatchState {
	Node(int ID) {
		super(ID);
	}

	int visitCount;// 访问次数
	int tn;// 时间
	NodeType node_tpye;// 节点类型
	int childIndex;
	Action last_action;
	int position;
    Node parent;
    List<Node> children;
    
    // 添加子节点
    public void addChild() {
    	
    }
    // 模拟
    public void simulation() {
    	
    }
    
    // 选择子节点
    public Node selectChild() {
		return null;
    }
    
    // ucb公式
    public float ucb1() {
		return 0;
    }    
    
}
