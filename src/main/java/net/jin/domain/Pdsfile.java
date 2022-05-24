/**
 * 
 */
package net.jin.domain;

import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;

/**
 * @author njh
 *
 */
@Entity
@Table(name = "pds_file")
public class Pdsfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pdsField;
	
	@Column(length = 200)
	private String fullName;
	
	private Integer downCnt = 0;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;

	public Long getPdsField() {
		return pdsField;
	}

	public void setPdsField(Long pdsField) {
		this.pdsField = pdsField;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getDownCnt() {
		return downCnt;
	}

	public void setDownCnt(Integer downCnt) {
		this.downCnt = downCnt;
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
		return "Pdsfile [pdsField=" + pdsField + ", fullName=" + fullName + ", downCnt=" + downCnt + ", regDate="
				+ regDate + ", updDate=" + updDate + "]";
	}

	
}
