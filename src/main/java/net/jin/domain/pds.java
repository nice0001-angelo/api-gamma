/**
 * 
 */
package net.jin.domain;

import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.*;

/**
 * @author njh
 *
 */
@Entity
@Table(name="pds")
public class pds {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	
	private String itemName;
	
	private String description;
	
	//자료 파일과 연관관계 매핑
	//@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	//private List<PdsFile> pdsFiles = new ArrayList<PdsFile>();
	
	@Transient
	private String[] files;
	
	private Integer viewCnt = 0;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	

}
