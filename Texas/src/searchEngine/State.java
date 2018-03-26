package searchEngine;

import basic.Action;
import basic.Phase;

/**
 * 表示博弈状态的信息
 * @author Plonk
 *
 */
public class State {
	protected int ID;// 游戏编号
	protected int maxJettons;// 目前最大的下注筹码数量
	protected int[] input_jettons;// 本次游戏中每位玩家向彩池中投注的筹码总量 
	protected Action action[][];// 每个阶段玩家的下注行为序列
	
	protected Phase phase;// 当前游戏阶段
	protected boolean finished;// 游戏是否结束
	protected int boardCards[];// 桌上公共牌数量
	protected int holdCards[];// 每个玩家手中的底牌
	
	State(int ID) {
		this.ID = ID;
		phase = Phase.NULL;
		input_jettons = new int[2];// 二人博弈
		boardCards= new int[5];
		holdCards = new int[2];
	}
}
