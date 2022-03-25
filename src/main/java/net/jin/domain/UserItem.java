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
@EqualsAndHashCode(of = "userItemNo")
@Entity
@Table(name = "user_item")
public class UserItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userItemNo;
	
	private Long userNo;
	
	private Long itemId;
	
	@Transient
	private String itemName;
	
	@Transient
	private Integer price;
	
	@Transient
	private String description;
	
	@Transient
	private String pictureUrl;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern = "yyyy-MM dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;

	public Long getUserItemNo() {
		return userItemNo;
	}

	public void setUserItemNo(Long userItemNo) {
		this.userItemNo = userItemNo;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
		return "UserItem [userItemNo=" + userItemNo + ", userNo=" + userNo + ", itemId=" + itemId + ", itemName="
				+ itemName + ", price=" + price + ", description=" + description + ", pictureUrl=" + pictureUrl
				+ ", regDate=" + regDate + ", updDate=" + updDate + "]";
	}
	
}
