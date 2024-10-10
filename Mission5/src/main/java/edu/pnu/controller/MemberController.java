package edu.pnu.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController  // = @Controller + @ResponseBody
//@Slf4j
public class MemberController {

	@Autowired
	private MemberService ms;

	//검색(Read - select)
	@GetMapping("/members")
	public List<MemberDTO> getAllMember() throws SQLException{
		return ms.getAllMember();
	}
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) throws SQLException {
		return ms.getMemberById(id);
	}

	//입력(Create - insert)
	@PostMapping("/member")
	public MemberDTO addMember(MemberDTO MemberDTO) throws SQLException {
		return ms.addMember(MemberDTO);
	}
	
	@PostMapping("/memberJSON")
	public MemberDTO addMemberJSON(@RequestBody MemberDTO MemberDTO) throws SQLException {
		return ms.addMember(MemberDTO);
	}

	//수정(Update - update)
	@PutMapping("/member")
	public int updateMember(MemberDTO MemberDTO) throws SQLException {
		return ms.updateMember(MemberDTO);
	}

	//삭제(Delete - delete)
	@DeleteMapping("/member")
	public int deleteMember(Integer id) throws SQLException {
		return ms.deleteMember(id);
	}
	
	
	//로그,,,,?
	@GetMapping("/log")
	public void getLogs() {
//		log.trace("trace message");
//		log.info("info message");
//		log.debug("debug message");
//		log.error("error message");
//		log.warn("warn message");
	}
}
