/**
 * 
 */
package net.jin.domain;

import java.time.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@UpdateTimestamp
	private LocalDateTime updDate;
}
