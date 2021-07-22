/**
 * 
 */
package net.jin.domain;

import java.io.*;

import lombok.*;

/**
 * @author njh
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of= {"groupCode","codeValue"})
public class CodeDetailId implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	private String groupCode;
	private String codeValue;
}
