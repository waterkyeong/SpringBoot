package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;
	
	@Builder
	public MemberDTO(Integer id, String pass, String name, Date regidate) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.regidate = regidate;
	}

	public MemberDTO() {
		
	}
}
