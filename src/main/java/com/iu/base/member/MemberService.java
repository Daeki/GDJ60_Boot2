package com.iu.base.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.error("=========== Spring Security Login ==============");
		log.error("=========== {} ======== ", username );
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}

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
			 bindingResult.rejectValue("username", "member.id.duplicate");
		 }
		
		return result;
	}
	
	public int setJoin(MemberVO memberVO)throws Exception{
		//memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
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
