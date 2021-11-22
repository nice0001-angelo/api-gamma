/**
 * 
 */
package net.jin.domain;

import java.time.*;

import javax.persistence.*;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.*;

import jdk.vm.ci.meta.*;
import lombok.*;

/**
 * @author njh
 *
 */
@Setter
@Getter
@EqualsAndHashCode(of="historyNo")
@ToString
@Entity
@Table(name="charge_coin_history")
public class ChargeCoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long historyNo;
	
	private Long userNo;
	private int amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime regDate;
	
}
