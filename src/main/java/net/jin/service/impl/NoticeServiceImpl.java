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
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

	NoticeRepository noticeRepository;
	
	//전체목록조회
	@Override
	public List<Notice> list() throws Exception{
		return noticeRepository.findAll(Sort.by(Direction.DESC,"noticeNO"));
	}
	
	//상세목록조회
	@Override
	public Notice read(Long noticeNo) throws Exception{
		return noticeRepository.getById(noticeNo);
	}
	
	//등록
	@Override
	public Notice insert(Notice notice) throws Exception{
		 noticeRepository.save(notice);
		 return noticeRepository.getById(notice.getNoticeNo());
	}
	
	//삭제
	@Override
	public void delete(Long noticeNo) throws Exception{
		noticeRepository.deleteById(noticeNo);
	}
	
	//수정
}
