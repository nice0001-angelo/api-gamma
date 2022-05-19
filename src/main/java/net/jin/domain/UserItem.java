package net.jin.domain;

import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of="userItemNo")
@ToString
@Entity
@Table(name="user_item")
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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;

}
