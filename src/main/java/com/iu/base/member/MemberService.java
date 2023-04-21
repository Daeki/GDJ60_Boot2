package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int setJoin(MemberVO memberVO)throws Exception{
		memberVO.setEnabled(true);
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("userName", memberVO.getUserName());
		map.put("num", 3);
		result = memberDAO.setMemberRole(map);
		
		return result;
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception{
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberDAO.getLogin(memberVO);
	}
}
