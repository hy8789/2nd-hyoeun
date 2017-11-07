package com.prj.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.prj.web.entity.Free;
import com.prj.web.entity.Voting;
import com.prj.web.service.admin.BoardService;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("free")
	public String advice(@RequestParam(value = "p", defaultValue = "1") int page, Model model) {
		List<Free> list = service.getFreeList(page);
		
		model.addAttribute("list", list);
		return "admin.board.free.list";
	}
	
	@RequestMapping("free/{id}")
	public String freeDetail(@PathVariable("id") String id, Model model) {

		model.addAttribute("f", service.getFree(id));
		model.addAttribute("prev", service.getFreePrev(id));
		model.addAttribute("next", service.getFreeNext(id));

		return "admin.board.notice.detail";
	}
	/////////////////////////////////////// 투표하기(Voting)   ////////////////////////////////////////////////////
	@RequestMapping("voting")
	public String voting(@RequestParam(value = "p", defaultValue = "1") int page, Model model) {
		List<Voting> list = service.getVotingList(page);
		
		model.addAttribute("list", list);
		return "admin.board.voting.list";
	}
	@RequestMapping("voting/{id}")
	public String votingDetail(@PathVariable("id") String id, Model model) {

		model.addAttribute("v", service.getVoting(id));
		model.addAttribute("prev", service.getVotingPrev(id));
		model.addAttribute("next", service.getVotingNext(id));

		return "admin.board.voting.detail";
	}
	
	@RequestMapping(value = "voting/reg", method = RequestMethod.GET)
	public String votingReg() {
		return "admin.board.voting.reg";
	}
	/*@RequestMapping(value = "voting/reg", method = RequestMethod.POST)
	public String votingReg(Voting voting) {
		String writerId = "hy8789";
		voting.setWriterId(writerId);
		// ������
		int row = service.insert(voting);

		return "redirect:../voting/detail";
	}*/
	@RequestMapping(value = "voting/reg", method = RequestMethod.POST)
	public String votingReg(Voting voting, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		String nextId = service.getVotingNextId();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		/*ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/customer/voting/%s/%s", year, nextId));

		System.out.println(path);

		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs())
				System.out.println("�⵵ ���丮 ���� �Ұ�");
		}*/

		/*path += File.separator + file.getOriginalFilename();
		File f2 = new File(path);*/

		/*InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);
*/
		/*byte[] buf = new byte[1024];

		int size = 0;
		while ((size = fis.read(buf)) > 0)
			fos.write(buf, 0, size);

		fos.close();
		fis.close();*/

		/*String fileName = file.getOriginalFilename();
*/
		String writerId = principal.getName();
	      System.out.println(writerId);
		voting.setWriterId(writerId);

		System.out.println(voting.getTitle());

		// noticeDao.insert(title, content, writerId);
		// ������
		int row = service.insert(voting);
		//memberDao.pointUp(principal.getName());

		// noticefileDao.insert(new NoticeFile(null, fileName, nextId));

		return "redirect:../voting";
	}
	
	@RequestMapping(value = "voting/{id}/edit", method = RequestMethod.GET)
	public String votingEdit(@PathVariable("id") String id,Model model) {
		model.addAttribute("up",service.getVoting(id));
		return "admin.board.voting.edit";
	}
	
/*	@RequestMapping(value = "voting/edit", method = RequestMethod.POST)
	public String votingEdit(Voting voting) {
		
		// ������
		int row = service.edit(voting);

		return "redirect:../voting/detail";
	}*/

	   
	  
	@RequestMapping(value = "voting/{id}/edit", method = RequestMethod.POST)
	public String votingEdit(@PathVariable("id") String id,Voting voting, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		//String nextId = service.getVotingNextId();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		/*ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/customer/voting/%s/%s", year, nextId));

		System.out.println(path);

		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs())
				System.out.println("�⵵ ���丮 ���� �Ұ�");
		}

		path += File.separator + file.getOriginalFilename();
		File f2 = new File(path);

		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);

		byte[] buf = new byte[1024];

		int size = 0;
		while ((size = fis.read(buf)) > 0)
			fos.write(buf, 0, size);

		fos.close();
		fis.close();

		String fileName = file.getOriginalFilename();*/

		//String writerId = "robin";
		//voting.setWriterId(writerId);
		//String writerId = principal.getName();
		//voting.setWriterId(writerId);
		//voting.getWriterId();
		//int row =service.infoUpdate(id,voting);
		//int iid= Integer.parseInt(id);
		voting.setId(Integer.parseInt(id));
		System.out.println(voting.getTitle());
		System.out.println(voting.getContent());
		System.out.println(voting.getId());
		//System.out.println(iid);
		// ������
		int row = service.edit(voting);
		return "redirect:../voting/{id}";
		
	}
}