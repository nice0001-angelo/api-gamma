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
@EqualsAndHashCode(of = "userItemNo")
@ToString
@Entity
@Table(name = "user_item")
public class UserItem {

}
