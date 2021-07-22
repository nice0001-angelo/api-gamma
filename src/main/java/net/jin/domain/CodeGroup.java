/**
 * 
 */
package net.jin.domain;

import java.time.*;
import java.util.*;

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
@JsonIgnoreProperties(value="hibernateLazeInitializer")
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(of="groupCode")
@Table(name="code_group")
public class CodeGroup {

	@Id
	@Column(length=3)
	private String groupCode;
	
	@Column(length=30, nullable=false)
	private String groupName;
	
	@Column(length=1)
	private String useYn = "Y";
	
	//CodeDetail과 연관관계 매핑 FK 관계 형성. CodeDetail 테이블과 groupCode로 Join 해서 결과값을 List로 리턴
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="groupCode")
	private List<CodeDetail> codeDetails;
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
