package GeneSequenceComparison;

/**
 * 基因序列实体类
 * 
 * @author Mr.洛洛
 * @time 20160112
 *
 */
public class Gene {
	
	/**
	 * 属性列表
	 */
	private String GeneList= "ATCG";
	
	private String gene = "";
	
	/**
	 * 构造方法
	 */
	public Gene() {}
	
	public Gene(String gene) {
		this.gene = gene;
	}
	
	/**
	 * 检查基因序列格式
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
