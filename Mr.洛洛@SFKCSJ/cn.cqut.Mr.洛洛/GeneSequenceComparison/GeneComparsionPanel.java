package GeneSequenceComparison;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 基因比较---动态规划+回溯
 * 
 * @author Mr.洛洛
 * @time 20160112
 */
public class GeneComparsionPanel extends JPanel{
	
	/**
	 * 版本ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 基因序列
	 */
	private String gen1, gen2;
	private String[][] arrayResult;
	
	/**
	 * 存储指针的数组
	 * 0: 没有来源
	 * 1： 左上方
	 * 2： 左方
	 * 3： 上方
	 */
	private Integer[][] points;
	
	/**
	 * 基因的最大公共子序列和转换后的基因序列 
	 */
	public ArrayList<String> geneLCS, gene1, gene2;
	// 基因的相似值
	public int value;
	
	/**
	 * 构造方法
	 */
	public GeneComparsionPanel(){}
		public GeneComparsionPanel(String gen1, String gen2) {
		this.gen1 = gen1;
		this.gen2 = gen2;
		this.geneLCS = new ArrayList<String>();
		this.gene1 = new ArrayList<String>();
		this.gene2 = new ArrayList<String>();
		this.arrayResult = new String[gen2.length() + 2][gen1.length() + 2];
		this.points = new Integer[gen2.length() + 1][gen1.length() + 1];
		
		initArrayResult();
		LCS();
		getTraceback();
		computeValue();
	}
	
	/**
	 * 填充二维数组
	 */
	private void initArrayResult() {
		for (int i = 0; i < this.arrayResult.length; i++) {
			for (int j = 0; j < this.arrayResult[i].length; j++) {
				if((i==0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
					this.arrayResult[i][j] = "-";
				}else if(i == 0 && j > 1){
					this.arrayResult[i][j] = "" + this.gen1.charAt(j-2);
				}else if(j == 0 && i > 1) {
					this.arrayResult[i][j] = "" + this.gen2.charAt(i-2);
				}else {
					this.arrayResult[i][j] = "";
				}
			}
		}
	}
	
	/**
	 * 填充矩阵
	 */
	private void LCS() {
		int value1, value2, value3;
		for (int i = 1; i < this.arrayResult.length; i++) {
			for (int j = 1; j <this.arrayResult[i].length; j++) {
				if (i == 1 || j == 1) { // 若i=1 或 j=1
					this.arrayResult[i][j] = intToString(0);
					this.points[i-1][j-1] = 0;
				} else {
					value1 = 0; // 存储当前单元格左上角单元格的数值
					value2 = stringToInt(this.arrayResult[i][j-1]); // 存储当前单元格左边单元格的数值
					value3 = stringToInt(this.arrayResult[i-1][j]); // 存储当前单元格上边单元格的数值
					// 如果所对应的字符相等，则value1为左上角单元格的数值加1
					if (this.gen2.charAt(i - 2) == this.gen1.charAt(j - 2)) {
						value1 = stringToInt(this.arrayResult[i - 1][j - 1]) + 1;
					}else {	// 如果所对应的字符不相等，则value1为左上角单元格的数值
						value1 = stringToInt(this.arrayResult[i - 1][j - 1]);
					}
					this.arrayResult[i][j] = intToString(maxValue(value1, value2, value3, i, j));
				}
			}
		}
		
		for (int i = 0; i < this.arrayResult.length; i++) {
			for (int j = 0; j < this.arrayResult[i].length; j++) {
				System.out.print("\t" + this.arrayResult[i][j]);
			}
			System.out.println();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.drawString("最长公共子序列排布", 300, 50);
		g.setColor(Color.gray);
		for (int i = 0; i < this.arrayResult.length; i++) {
			for (int j = 0; j <this.arrayResult[i].length; j++) {
				if (i == 1 || j == 1) { // 若i=1 或 j=1
					this.arrayResult[i][j] = intToString(0);
				} else {
					if (i > 1 && j > 1
							&& (this.gen1.charAt(i - 2) == this.gen2.charAt(j - 2))) {	 // 若i>0, j>0, 且ai = bj
						this.arrayResult[i][j] = intToString(stringToInt(this.arrayResult[i - 1][j - 1]) + 1);
						
					}
					if (i > 0 && j > 0
							&& (this.gen1.charAt(i - 2) != this.gen2.charAt(j - 2))) {	// 若i>0, j>0,且ai != bj
						this.arrayResult[i][j] = intToString(stringToInt(this.arrayResult[i][j - 1]) > stringToInt(this.arrayResult[i - 1][j]) ? stringToInt(this.arrayResult[i][j-1]) : stringToInt(this.arrayResult[i-1][j]));
					}
				}
			}
		}
	}
	
	/**
	 * 得到最大公共子序列---回溯法
	 */
	private void getTraceback() {
		int row = this.gen2.length() + 1;
		int column = this.gen1.length() + 1;
		String str = this.arrayResult[row][column];
		int temp = stringToInt(str);
		while(temp != 0) {
			// 获取指针
			int point = this.points[row-1][column-1];
			switch (point) {
			case 1:
				// 来自左上方
				if(this.arrayResult[0][column].equals(this.arrayResult[row][0])) { //如果是相等的字符，则加入公共子串中
					this.geneLCS.add(0, this.arrayResult[row][0]);
				}
				this.gene1.add(0, this.arrayResult[0][column]);
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
				column -= 1;
				break;
			case 2: 
				// 来自左方
				this.gene2.add(0,"-");
				this.gene1.add(0, this.arrayResult[0][column]);
				column -= 1;
				break;
			case 3:
				// 来自上方
				this.gene1.add(0, "-");
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
				break;
			default:
				break;
			}
			temp = stringToInt(this.arrayResult[row][column]);
		}
		while(!(this.arrayResult[0][column].equals("-") && this.arrayResult[row][0].equals("-"))){ //不同时等于空格
			if((!this.arrayResult[0][column].equals("-")) && (!this.arrayResult[row][0].equals("-"))){
				this.gene2.add(0, this.arrayResult[row][0]);
				this.gene1.add(0, this.arrayResult[0][column]);
				row -= 1;
				column -= 1;
			}else if(this.arrayResult[0][column].equals("-")) { // 0所在位置的行为空，则需要在gene1头部加上一个空格, 且把gene2头部的字符加上
				this.gene1.add(0, "-");
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
			}else {	// 0所在位置的列为空，则需要在gene2头部加上一个空格，且把gene1头部的字符加上
				this.gene2.add(0, "-");
				this.gene1.add(0, this.arrayResult[0][column]);
				column -= 1;
			}
		}
	}
	
	/**
	 * 获取计算出的二维数组数据
	 * @return
	 */
	public String[][] getArrayResult() {
		return this.arrayResult;
	}
	
	public Integer[][] getPoints() {
		return this.points;
	}
	
	/**
	 * 计算两个基因序列的相似值
	 */
	private void computeValue() {
		for (int i = 0; i < this.gene1.size(); i++) {
			this.value += computeGene(this.gene1.get(i), this.gene2.get(i));
		}
	}
	
	/**
	 * 两个单基因比较值
	 * @param g1
	 * @param g2
	 * @return
	 */
    public static int computeGene(String g1, String g2)
    {
        if (g1.equals(g2))
            return 5;
        else if ((g1.equals("A") && g2.equals("C")) || (g1.equals("C") && g2.equals("A")))//A
            return -1;
        else if ((g1.equals("A") && g2.equals("G")) || (g1.equals("G") && g2.equals("A")))
            return -2;
        else if ((g1.equals("A") && g2.equals("T")) || (g1.equals("T") && g2.equals("A")))
            return -1;
        else if ((g1.equals("A") && g2.equals("-")) || (g1.equals("-") && g2.equals("A")))
            return -3;
        else if ((g1.equals("C") && g2.equals("G")) || (g1.equals("G") && g2.equals("C")))//C
            return -3;
        else if ((g1.equals("C") && g2.equals("T")) || (g1.equals("T") && g2.equals("C")))
            return -2;
        else if ((g1.equals("C") && g2.equals("-")) || (g1.equals("-") && g2.equals("C")))
            return -4;
        else if ((g1.equals("G") && g2.equals("T")) || (g1.equals("T") && g2.equals("G")))//G
            return -2;
        else if ((g1.equals("G") && g2.equals("-")) || (g1.equals("-") && g2.equals("G")))
            return -2;
        else if ((g1.equals("T") && g2.equals("-")) || (g1.equals("-") && g2.equals("T")))//G
            return -1;
        return 0;
    }
	
	/**
	 * 返回三个数的最大值
	 * @param num1
	 * @param num2
	 * @param num3
	 * @param row
	 * @param col
	 * @return
	 */
	private int maxValue(int num1, int num2, int num3, int row, int col) {
		int max = num1 >= num2 ? num1 : num2;
		max = max >= num3 ? max : num3;
		
		// 填写来源指针
		if(max == num1) {
			this.points[row-1][col-1] = 1;
		}else if(max == num2) {
			this.points[row-1][col-1] = 2;
		}else {
			this.points[row-1][col-1] = 3;
		}
		return max;
	}
	
	/**
	 * Integer值转成String
	 * @param num
	 * @return
	 */
	private String intToString(int num) {
		return String.valueOf(num);
	}
	
	/**
	 * String值转换成Integer
	 * @param str
	 * @return
	 */
	private int stringToInt(String str) {
		return Integer.parseInt(str);
	}
}
