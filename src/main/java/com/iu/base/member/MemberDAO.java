package com.iu.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public int setEnabled()throws Exception;
	
	public int setLastTimeUpdate(MemberVO memberVO)throws Exception;
	
	public List<MemberVO> getMembers()throws Exception;
	
	public int setJoin(MemberVO memberVO)throws Exception;
	
	public int setMemberRole(Map<String, Object> map)throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO)throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception;

}
