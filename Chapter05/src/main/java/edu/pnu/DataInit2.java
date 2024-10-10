package edu.pnu;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

//	 현재 서버가 꺼지면 메모리가 날아가는 버전으로 하고있기 때문에 만들어 놓은 클래스.
@RequiredArgsConstructor	//autowired를 안쓰고 대신 사용하는것.
@Component
public class DataInit2 implements ApplicationRunner { //Applicationrunner => 서버 구동 시 자동 실행 인터페이스(제일 먼저 구동함)
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		Member m1 = Member.builder()
				.id("mdmber1")
				.password("member111")
				.name("둘리")
				.role("User")
				.build();
		memberRepo.save(m1);
		Member m2 = Member.builder()
				.id("mdmber2")
				.password("member222")
				.name("고길동")
				.role("Admin")
				.build();
		memberRepo.save(m2);
		
		for(int i =1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
					.content("content1"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m1)
					.build()
			);
		}
		
		for(int i =1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
					.content("content2"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m2)
					.build()
					);
		}
		
	}
}
