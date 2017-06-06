//タグ分析で使う汎用エンティティ
package Carm;

public class EntityAnalyticalData {
	
	private String tagName;  //タグ名
	private int payment; //利用金額
	private int count; //利用回数
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
