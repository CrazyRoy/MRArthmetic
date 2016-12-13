package BoardCover;

import javax.swing.JFrame;

/**
 * 棋盘覆盖测试类
 * 
 * @author Mr.洛洛
 * @time 20160113
 * 
 */
public class TestBoardCover {
	public static void main(String args[]) {
		BoardCoverFrame test = new BoardCoverFrame();
		test.setTitle("算法分析与设计课程设计---棋盘覆盖");
		test.setSize(700, 500);
		test.setLocationRelativeTo(null); // Center in frame
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
