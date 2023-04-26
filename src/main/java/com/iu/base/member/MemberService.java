package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int setLastTimeUpdate(MemberVO memberVO)throws Exception{
		return memberDAO.setLastTimeUpdate(memberVO);
	};
	
	//패스워드가 일치하는지 검즈하는 메서드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean result=false;
		//false : error가 없음 , 검증성공
		//true  : error가 실패 , 검증 실패
		
		//1. annotation 검증 결과
		result= bindingResult.hasErrors();
		
		//2. password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			result=true;
			bindingResult.rejectValue("passwordCheck", "member.password.notEqual");
		}
		
		//3. ID중복 검사
		 MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		 if(checkMember != null) {
			 result=true;
			 bindingResult.rejectValue("userName", "member.id.duplicate");
		 }
		
		return result;
	}
	
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
