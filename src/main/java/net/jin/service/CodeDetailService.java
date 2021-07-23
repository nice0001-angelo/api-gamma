/**
 * 
 */
package net.jin.service;

import org.springframework.stereotype.*;

import net.jin.domain.*;

/**
 * @author njh
 *
 */
@Service
public interface CodeDetailService {
	
	public CodeDetail read(CodeDetail codeDetail) throws Exception;

}
