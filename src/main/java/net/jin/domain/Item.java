/**
 * 
 */
package net.jin.domain;

import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

/**
 * @author njh
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	
	@Column(length = 50, nullable = false)
	private String itemName;
	
	private Integer price;
	
	@Column(length = 250)
	private String description;
	
	@Transient
	private MultipartFile picture;
	
	@Column(length = 200)
	private String pictureUrl;
	
	@Transient
	private MultipartFile preview;
	
	@Column(length = 200)
	private String previewUrl;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
