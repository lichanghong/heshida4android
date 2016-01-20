package heshida.haihong.com.heshida.Comment;
/**
 * 表情对象
 * @author daobo.yuan
 *
 */
public class Expression {
	int drableId;//表情图像资源ID
	String code;//表情代码

	public Expression() {
		super();
	}

	public Expression(int drableId, String code) {
		super();
		this.drableId = drableId;
		this.code = code;
	}

	public int getDrableId() {
		return drableId;
	}

	public void setDrableId(int drableId) {
		this.drableId = drableId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
