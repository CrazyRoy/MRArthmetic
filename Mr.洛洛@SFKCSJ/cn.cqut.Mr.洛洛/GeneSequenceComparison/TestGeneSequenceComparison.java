package GeneSequenceComparison;

import javax.swing.JFrame;

/**
 * 基因序列比较测试类
 * 
 * @author Mr.洛洛
 * @time 20160111
 */
public class TestGeneSequenceComparison {

	public static void main(String args[]) {
		GeneComparisonFrame test = new GeneComparisonFrame();
		test.setTitle("算法分析与设计课程设计---基因序列比较");
		test.setSize(700, 600);
		test.setLocationRelativeTo(null); // Center in frame
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
