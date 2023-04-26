package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private MemberDAO memberDAO;
	
	@Scheduled(cron = "10 * * * * *")
	public void test() throws Exception {
	
		memberDAO.setEnabled();
		
		
	}

}
