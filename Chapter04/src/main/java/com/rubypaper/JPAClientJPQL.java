package com.rubypaper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.rubypaper.domain.Board;

public class JPAClientJPQL {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();

		//글 목록 조회
		try {
//			String jpql = "select b from Board b order by b.seq asc";
//			TypedQuery<Board> tq = em.createQuery(jpql,Board.class);
//			List<Board> list = tq.getResultList();
//			for(Board b : list) {
//				System.out.println(b);
//			}

			String sql = "select b.title from Board b order by b.seq asc";
			Query q = em.createNativeQuery(sql);
			List<Object[]> list = q.getResultList();
			for(Object[] s : list) {
				for(Object o : s)
					System.out.println(o.toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
