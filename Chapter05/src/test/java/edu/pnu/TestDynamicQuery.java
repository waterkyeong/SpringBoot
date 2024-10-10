package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class TestDynamicQuery {
	
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "TITLE";
		String searchKeyword = "title10";
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.contains(searchKeyword));
		}else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardlist = boardRepo.findAll(builder, paging);
		
		System.out.println("검색결과");
		for(Board board : boardlist) {
			System.out.println("--->"+board.toString());
		}
		
	}
}
