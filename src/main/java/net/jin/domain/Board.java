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
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of = "boardNo")
@Table(name = "board")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNo;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	@Lob
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
