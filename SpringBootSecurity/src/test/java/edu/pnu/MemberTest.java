package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.repository.MemberRepository;

@SpringBootTest
public class MemberTest {

	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void test01() {
		memberRepo.save(Member.builder()
				.username("a")
				.password("b")
				.role(Role.ROLE_MEMBER)
				.enabled(true).build());
	}
}
