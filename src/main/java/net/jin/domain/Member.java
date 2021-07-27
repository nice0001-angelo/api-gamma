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
import javax.validation.constraints.*;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

/**
 * @author njh
 *
 */
@JsonIgnoreProperties(value="hibernateLazyInitializer")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="userNo")
@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_no")
	private Long userNo;
	
	@NotBlank
	@Column(name="user_id", length=50, nullable=false)
	private String userId;
	
	@NotBlank
	@Column(length=200, nullable=false)
	private String userPw;
	
	@NotBlank
	@Column(length=100, nullable=false)
	private String userName;
	
	@Column(length=3, nullable=false)
	private String job;
	
	private int coin;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
	
	//회원권한과 연관관계 매핑
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_no")
	private List<MemberAuth> authList = new ArrayList<MemberAuth>();
	
	public void addAuth(MemberAuth auth) {
		authList.add(auth);
	}
	
	public void clearAuthList() {
		authList.clear();
	}
	
	
	
	
	
	
}
