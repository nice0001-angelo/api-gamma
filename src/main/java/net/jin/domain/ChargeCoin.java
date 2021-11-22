/**
 * 
 */
package net.jin.domain;

import javax.persistence.*;

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
}
