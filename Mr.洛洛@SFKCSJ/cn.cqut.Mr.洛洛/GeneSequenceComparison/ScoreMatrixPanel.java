package GeneSequenceComparison;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 基因分值矩阵
 * @author Mr.洛洛
 * @time 20160111
 */

public class ScoreMatrixPanel extends JPanel{

	/**
	 * 版本ID
	 */
	private static final long serialVersionUID = 1L;

	// 分值矩阵规则排布图
	private String[][] matrix = {
			                     {" ", "A", "C", "G", "T", "-"}, 
								 {"A", "5", "-1", "-2", "-1", "-3"},
								 {"C", "-1", "5", "-3", "-2", "-4"}, 
								 {"G", "-2", "-3", "5", "-2", "-2"},
								 {"T", "-1", "-2", "-2", "5", "-1"},
								 {"-", "-3", "-4", "-2", "-1", "*"}
								};
	
	public boolean drawArray = false;
	private String[][] arrayResult;
	private Integer[][] points;
	
	// 画图起点
	private int drawStart = 0;
	
	public ScoreMatrixPanel(){}
	
	public void setArrayResult(String[][] array) {
		this.arrayResult = array;
		this.drawArray = true;
		this.repaint();
	}
	
	public void setDrawArray(boolean flag) {
		this.drawArray = flag;
		this.repaint();
	}
	
	public void setPoints(Integer[][] points) {
		this.points = points;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		int width = 0;
		int height = 30;
		int XStart = 0;
		int YStart = 80;
		int length = 0;
		
		Image image = new ImageIcon("bgImage.jpg").getImage();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.red);
		if(this.drawArray) {
			this.drawStart = getWidth()/16;
			width = getWidth()/17;
			g.drawString("基因的分值矩阵", getWidth()*1/6, 50);
			g.drawString("两对基因序列的LCS表格", getWidth()*2/3, 50);
		}else {
			this.drawStart = getWidth()/7;
			width = getWidth()/8;
			g.drawString("基因的分值矩阵", getWidth()*4/9, 50);
		}
		g.setColor(Color.gray);
		
		for (int i = 0; i < matrix.length; i++) {
			XStart = drawStart;
			for (int j = 0; j < matrix[i].length; j++) {
				g.drawLine(XStart, YStart, XStart + width, YStart);
				if(i==0 || j==0) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.red);
				}
				g.drawString(matrix[i][j], XStart + (width*2/5), YStart + (height*3/5));
				g.setColor(Color.gray);
				g.drawLine(XStart, YStart, XStart, YStart + height);
				XStart += width;
			}
			g.drawLine(XStart, YStart, XStart, YStart + height);
			length = XStart;
			YStart += height;
		}
		g.drawLine(drawStart, YStart, length, YStart);
		
		if(drawArray) {
			drawStart = length + 30 ;
			YStart = 80;
			for (int i = 0; i < this.arrayResult.length; i++) {
				XStart = drawStart;
				for (int j = 0; j < this.arrayResult[i].length; j++) {
					g.drawLine(XStart, YStart, XStart + width, YStart);
					if(i==0 || j==0) {
						g.setColor(Color.black);
					} else {
						g.setColor(Color.red);
					}
					g.drawString(this.arrayResult[i][j], XStart + (width*2/5), YStart + (height*3/5));
					if(i>0 && j > 0) {
						g.setColor(Color.blue);
						switch (this.points[i-1][j-1]) {
						case 1:
							g.drawLine(XStart - (width*2/5), YStart - (height*2/5), XStart + (width*2/5), YStart + (height*3/5));
							break;
						case 2:
							g.drawLine(XStart - (width*2/5), YStart + (height*3/5),XStart + (width*2/5), YStart + (height*3/5));
							break;
						case 3:
							g.drawLine(XStart + (width*2/5), YStart - (height*3/5), XStart + (width*2/5), YStart + (height*3/5));
							break;
						default:
							break;
						}
					}
					g.setColor(Color.gray);
					g.drawLine(XStart, YStart, XStart, YStart + height);
					XStart += width;
				}
				g.drawLine(XStart, YStart, XStart, YStart + height);
				length = XStart;
				YStart += height;
			}
			g.drawLine(drawStart, YStart, length, YStart);
		}
	}
}
