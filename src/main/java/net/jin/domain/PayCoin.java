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

import lombok.*;

/**
 * @author njh
 *
 */
//롭복에서 값의 내용이 같은지 그리고 객체가 같은지 비교하는 equals와 hashcode를 생성해주는 어노테이션
@EqualsAndHashCode(of = "historyNo")
@ToString
@Entity
@Table(name = "pay_coin_history")
public class PayCoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long historyNo;
	
	private Long userNo;
	private Long itemId;
	
	@Transient
	private String itemName;
	
	private int amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;

	public Long getHistoryNo() {
		return historyNo;
	}

	public void setHistoryNo(Long historyNo) {
		this.historyNo = historyNo;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
	
	
}
