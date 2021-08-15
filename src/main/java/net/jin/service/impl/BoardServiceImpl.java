/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.stereotype.*;

import lombok.*;
import net.jin.domain.*;
import net.jin.repository.*;
import net.jin.service.*;

/**
 * @author njh
 *
 */
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardRepository boardRepository;
	
	//전체목록조회
	@Override
	public List<Board> list() throws Exception{
		return boardRepository.findAll();
	}

}
