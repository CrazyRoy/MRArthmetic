package GeneSequenceComparison;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * ����Ƚ�---��̬�滮+����
 * 
 * @author Mr.����
 * @time 20160112
 */
public class GeneComparsionPanel extends JPanel{
	
	/**
	 * �汾ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ��������
	 */
	private String gen1, gen2;
	private String[][] arrayResult;
	
	/**
	 * �洢ָ�������
	 * 0: û����Դ
	 * 1�� ���Ϸ�
	 * 2�� ��
	 * 3�� �Ϸ�
	 */
	private Integer[][] points;
	
	/**
	 * �������󹫹������к�ת����Ļ������� 
	 */
	public ArrayList<String> geneLCS, gene1, gene2;
	// ���������ֵ
	public int value;
	
	/**
	 * ���췽��
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
	 * ����ά����
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
	 * ������
	 */
	private void LCS() {
		int value1, value2, value3;
		for (int i = 1; i < this.arrayResult.length; i++) {
			for (int j = 1; j <this.arrayResult[i].length; j++) {
				if (i == 1 || j == 1) { // ��i=1 �� j=1
					this.arrayResult[i][j] = intToString(0);
					this.points[i-1][j-1] = 0;
				} else {
					value1 = 0; // �洢��ǰ��Ԫ�����Ͻǵ�Ԫ�����ֵ
					value2 = stringToInt(this.arrayResult[i][j-1]); // �洢��ǰ��Ԫ����ߵ�Ԫ�����ֵ
					value3 = stringToInt(this.arrayResult[i-1][j]); // �洢��ǰ��Ԫ���ϱߵ�Ԫ�����ֵ
					// �������Ӧ���ַ���ȣ���value1Ϊ���Ͻǵ�Ԫ�����ֵ��1
					if (this.gen2.charAt(i - 2) == this.gen1.charAt(j - 2)) {
						value1 = stringToInt(this.arrayResult[i - 1][j - 1]) + 1;
					}else {	// �������Ӧ���ַ�����ȣ���value1Ϊ���Ͻǵ�Ԫ�����ֵ
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
		
		g.drawString("������������Ų�", 300, 50);
		g.setColor(Color.gray);
		for (int i = 0; i < this.arrayResult.length; i++) {
			for (int j = 0; j <this.arrayResult[i].length; j++) {
				if (i == 1 || j == 1) { // ��i=1 �� j=1
					this.arrayResult[i][j] = intToString(0);
				} else {
					if (i > 1 && j > 1
							&& (this.gen1.charAt(i - 2) == this.gen2.charAt(j - 2))) {	 // ��i>0, j>0, ��ai = bj
						this.arrayResult[i][j] = intToString(stringToInt(this.arrayResult[i - 1][j - 1]) + 1);
						
					}
					if (i > 0 && j > 0
							&& (this.gen1.charAt(i - 2) != this.gen2.charAt(j - 2))) {	// ��i>0, j>0,��ai != bj
						this.arrayResult[i][j] = intToString(stringToInt(this.arrayResult[i][j - 1]) > stringToInt(this.arrayResult[i - 1][j]) ? stringToInt(this.arrayResult[i][j-1]) : stringToInt(this.arrayResult[i-1][j]));
					}
				}
			}
		}
	}
	
	/**
	 * �õ���󹫹�������---���ݷ�
	 */
	private void getTraceback() {
		int row = this.gen2.length() + 1;
		int column = this.gen1.length() + 1;
		String str = this.arrayResult[row][column];
		int temp = stringToInt(str);
		while(temp != 0) {
			// ��ȡָ��
			int point = this.points[row-1][column-1];
			switch (point) {
			case 1:
				// �������Ϸ�
				if(this.arrayResult[0][column].equals(this.arrayResult[row][0])) { //�������ȵ��ַ�������빫���Ӵ���
					this.geneLCS.add(0, this.arrayResult[row][0]);
				}
				this.gene1.add(0, this.arrayResult[0][column]);
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
				column -= 1;
				break;
			case 2: 
				// ������
				this.gene2.add(0,"-");
				this.gene1.add(0, this.arrayResult[0][column]);
				column -= 1;
				break;
			case 3:
				// �����Ϸ�
				this.gene1.add(0, "-");
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
				break;
			default:
				break;
			}
			temp = stringToInt(this.arrayResult[row][column]);
		}
		while(!(this.arrayResult[0][column].equals("-") && this.arrayResult[row][0].equals("-"))){ //��ͬʱ���ڿո�
			if((!this.arrayResult[0][column].equals("-")) && (!this.arrayResult[row][0].equals("-"))){
				this.gene2.add(0, this.arrayResult[row][0]);
				this.gene1.add(0, this.arrayResult[0][column]);
				row -= 1;
				column -= 1;
			}else if(this.arrayResult[0][column].equals("-")) { // 0����λ�õ���Ϊ�գ�����Ҫ��gene1ͷ������һ���ո�, �Ұ�gene2ͷ�����ַ�����
				this.gene1.add(0, "-");
				this.gene2.add(0, this.arrayResult[row][0]);
				row -= 1;
			}else {	// 0����λ�õ���Ϊ�գ�����Ҫ��gene2ͷ������һ���ո��Ұ�gene1ͷ�����ַ�����
				this.gene2.add(0, "-");
				this.gene1.add(0, this.arrayResult[0][column]);
				column -= 1;
			}
		}
	}
	
	/**
	 * ��ȡ������Ķ�ά��������
	 * @return
	 */
	public String[][] getArrayResult() {
		return this.arrayResult;
	}
	
	public Integer[][] getPoints() {
		return this.points;
	}
	
	/**
	 * ���������������е�����ֵ
	 */
	private void computeValue() {
		for (int i = 0; i < this.gene1.size(); i++) {
			this.value += computeGene(this.gene1.get(i), this.gene2.get(i));
		}
	}
	
	/**
	 * ����������Ƚ�ֵ
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
	 * ���������������ֵ
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
		
		// ��д��Դָ��
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
	 * Integerֵת��String
	 * @param num
	 * @return
	 */
	private String intToString(int num) {
		return String.valueOf(num);
	}
	
	/**
	 * Stringֵת����Integer
	 * @param str
	 * @return
	 */
	private int stringToInt(String str) {
		return Integer.parseInt(str);
	}
}
