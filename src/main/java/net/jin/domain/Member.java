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
	@JoinColumn(name="user_no") //name= 에는 매핑할 외래키 이름을 지정한다
	private List<MemberAuth> authList = new ArrayList<MemberAuth>();
	
	public void addAuth(MemberAuth auth) {
		authList.add(auth);
	}
	
	public void clearAuthList() {
		authList.clear();
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
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

	public List<MemberAuth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<MemberAuth> authList) {
		this.authList = authList;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", job=" + job + ", coin=" + coin + ", regDate=" + regDate + ", updDate=" + updDate + ", authList="
				+ authList + "]";
	}
	
}
