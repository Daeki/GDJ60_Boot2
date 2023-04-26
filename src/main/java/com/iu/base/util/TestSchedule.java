package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private MailManager mailManager;
	
	@Scheduled(cron = "40 * * * * *")
	public void test() throws Exception {
		List<MemberVO> ar= memberDAO.getBirth();
		
		for(MemberVO memberVO:ar) {
			mailManager.send(memberVO.getEmail(), "생일축하", "<h1>생일축하</h1>");
		}
		
		
	
//		List<MemberVO> ar= memberDAO.getBirth();
//		
//		StringBuffer sb = new StringBuffer();
//		sb.append("오늘은 ");		
//		
//		for(MemberVO memberVO:ar) {
//			sb.append(memberVO.getName());
//			sb.append(", ");
//		}
//		
//		sb.append(" 생일 입니다. 생일빵 !!");
//		
//		
//		NoticeVO noticeVO = new NoticeVO();
//		
//		noticeVO.setTitle("생축");
//		noticeVO.setWriter("사장님");
//		noticeVO.setContents(sb.toString());
//		
//		noticeDAO.setInsert(noticeVO);
		
		
	}

}
