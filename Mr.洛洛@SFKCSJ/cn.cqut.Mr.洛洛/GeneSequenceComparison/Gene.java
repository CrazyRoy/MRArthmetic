package GeneSequenceComparison;

/**
 * ��������ʵ����
 * 
 * @author Mr.����
 * @time 20160112
 *
 */
public class Gene {
	
	/**
	 * �����б�
	 */
	private String GeneList= "ATCG";
	
	private String gene = "";
	
	/**
	 * ���췽��
	 */
	public Gene() {}
	
	public Gene(String gene) {
		this.gene = gene;
	}
	
	/**
	 * ���������и�ʽ
	 * @param gene
	 * @return
	 */
	boolean checkGeneFormat() {
		for (int i = 0; i < this.gene.length(); i++) {
			if(!this.GeneList.contains("" + this.gene.charAt(i))){
				return false;
			}
		} 
		return true;
	}
}
