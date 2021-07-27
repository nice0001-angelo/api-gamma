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
@Table(name="member_auth")
public class MemberAuth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userAuthNo;
	
	@Column(name="user_no")
	private Long userNo;
	
	@Column(length=50)
	private String auth;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
