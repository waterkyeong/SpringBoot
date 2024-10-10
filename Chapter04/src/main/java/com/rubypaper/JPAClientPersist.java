package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClientPersist {
	public static void main(String[] args) {
		//entitymanager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		//transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			//transaction 시작
			tx.begin();
			
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			//글등록
			em.persist(board);
			
//			굳이 위처럼 할 필요없이 밑의 코드처럼 바로 작성도 가능
//			em.persist(Board.builder()
//					.title("JPA 제목")
//					.writer("관리자")
//					.content("JPA 글 등록 잘 되네요.")
//					.createDate(new Date())
//					.cnt(0L)
//					.build());
			
			//더미데이터 생성 > persistence.xml에서 update >create로 바꾸고 실행
//			for(int i = 0; i<=10; i++) {
//				Board board1 = new Board();
//				
//				board1.setTitle("JPA 제목"+i);
//				board1.setWriter("관리자");
//				board1.setContent("더미 "+i);
//				board1.setCreateDate(new Date());
//				board1.setCnt(0L);
//				
//				em.persist(board1);
//			}
			
			//transaction commit
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
			emf.close();
		}
	}
}
