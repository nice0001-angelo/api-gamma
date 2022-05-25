/**
 * 
 */
package net.jin.domain;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;

/**
 * @author njh
 *
 */
@Entity
@Table(name = "pds")
public class Pds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	
	private String itemName;
	
	private String description;
	
	//자료 파일과 연관관계 매핑
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private List<Pdsfile> pdsFiles = new ArrayList<Pdsfile>();
	
	@Transient
	private String[] files;
	
	private Integer viewCnt = 0;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
	
	public void addItemFile(Pdsfile itemFile) {
		pdsFiles.add(itemFile);
	}
	
	public void clearItemfile() {
		pdsFiles.clear();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public Integer getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(Integer viewCnt) {
		this.viewCnt = viewCnt;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public LocalDateTime getUpdDate() {
		return updDate;
	}

	public void setUpdDate(LocalDateTime updDate) {
		this.updDate = updDate;
	}

	@Override
	public String toString() {
		return "pds [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", files="
				+ Arrays.toString(files) + ", viewCnt=" + viewCnt + ", regDate=" + regDate + ", updDate=" + updDate
				+ "]";
	}
	

}
