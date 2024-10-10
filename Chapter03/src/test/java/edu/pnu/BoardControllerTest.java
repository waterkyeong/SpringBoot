package edu.pnu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import edu.pnu.domain.BoardVO;

//@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BoardControllerTest {
	
	@Autowired
//	private MockMvc mockMvc;
	private TestRestTemplate restTemplate;
	
	@Test
	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name","둘리"))
//			.andExpect(status().isOk())
//			.andExpect((ResultMatcher) content().string("Hello : 둘리"))
//			.andDo(print());
		
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("Hello : 둘리",result);
	}
	
	@Test
	public void testGetBoard()throws Exception {
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWriter());
	}
	
}
