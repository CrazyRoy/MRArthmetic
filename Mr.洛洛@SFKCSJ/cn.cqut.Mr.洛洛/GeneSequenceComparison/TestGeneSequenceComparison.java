package GeneSequenceComparison;

import javax.swing.JFrame;

/**
 * �������бȽϲ�����
 * 
 * @author Mr.����
 * @time 20160111
 */
public class TestGeneSequenceComparison {

	public static void main(String args[]) {
		GeneComparisonFrame test = new GeneComparisonFrame();
		test.setTitle("�㷨��������ƿγ����---�������бȽ�");
		test.setSize(700, 600);
		test.setLocationRelativeTo(null); // Center in frame
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
