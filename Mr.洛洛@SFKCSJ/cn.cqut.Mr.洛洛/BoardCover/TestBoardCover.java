package BoardCover;

import javax.swing.JFrame;

/**
 * ���̸��ǲ�����
 * 
 * @author Mr.����
 * @time 20160113
 * 
 */
public class TestBoardCover {
	public static void main(String args[]) {
		BoardCoverFrame test = new BoardCoverFrame();
		test.setTitle("�㷨��������ƿγ����---���̸���");
		test.setSize(700, 500);
		test.setLocationRelativeTo(null); // Center in frame
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
