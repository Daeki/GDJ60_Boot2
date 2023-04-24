package com.iu.base.member;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	@Future
	private Date birth;
	private boolean enabled;
	private List<RoleVO> roleVOs;

}
