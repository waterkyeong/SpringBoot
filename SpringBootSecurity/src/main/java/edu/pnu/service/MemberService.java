package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	public void save(Member member) {
		memRepo.save(Member.builder()
					.username(member.getUsername())
					.password(encoder.encode(member.getPassword()))
					.role(Role.ROLE_MEMBER)
					.enabled(true).build());
	}
}
