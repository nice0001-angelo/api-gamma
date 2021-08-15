/**
 * 
 */
package net.jin.service.impl;

import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;
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
		return boardRepository.findAll(Sort.by(Direction.DESC,"boardNo"));
	}

	//상세목록조회
	@Override
	public Board read(Long boardNo) throws Exception{
		return boardRepository.getById(boardNo);
	}
	
	
	//등록
	@Override
	public Board insert(Board board) throws Exception{
		return boardRepository.save(board);
	}
	//삭제
	@Override
	public void delete(Long boardNo) throws Exception{
		boardRepository.deleteById(boardNo);
	}
	
	//수정
	@Override
	public Board update(Board board) throws Exception{
		return boardRepository.save(board);
	}
	
}
