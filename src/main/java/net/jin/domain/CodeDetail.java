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
@EqualsAndHashCode(of= {"groupCode","codeValue"})
@Entity
@IdClass(CodeDetailId.class) //CodeDetail의 ID를 별도의 클래스로 선언
@Table(name="code_detail")
public class CodeDetail {
	
	@Id
	@Column(length=3)
	private String groupCode;
	
	@Id
	@Column(length=3)
	private String codeValue;
	
	@Column(length=30, nullable=false)
	private String codeName;
	
	private int sortSeq;
	
	@Column(length=1)
	private String useYn="Y";
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
