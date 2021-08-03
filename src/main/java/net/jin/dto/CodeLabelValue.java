/**
 * 
 */
package net.jin.dto;

/**
 * @author njh
 * value: GroupCode
 * label: GroupName
 * 그룹코드와 그룹네임을 관리하기 위한 객체
 * Domain 이 아닌 View 형태의 객체
 */
public class CodeLabelValue {

	private String value;
	private String label;
	
	public CodeLabelValue() {
		super();
	}
	
	public CodeLabelValue(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
