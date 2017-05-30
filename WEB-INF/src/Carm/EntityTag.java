package Carm;

//タグ情報を格納するエンティティ
public class EntityTag {
	
	private int tagId;
	private int useId;
	private String tagName;
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
}
