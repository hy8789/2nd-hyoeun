package com.prj.web.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prj.web.dao.FreeDao;
import com.prj.web.dao.VotingDao;
import com.prj.web.entity.Free;
import com.prj.web.entity.Voting;

public class BoardService {
	
	@Autowired
	private FreeDao freeDao;
	
	public List<Free> getFreeList(int page){
		return freeDao.getList(page);
	}

	public Free getFree(String id) {
		return freeDao.getFree(id);
	}

	public Free getFreePrev(String id) {
		return freeDao.getFreePrev(id);
	}

	public Free getFreeNext(String id) {
		return freeDao.getFreeNext(id);
	}
	
	
//////////////////////      투표하기 (Voting)   ////////////////////////////
	@Autowired
	private VotingDao votingDao;
	
	public List<Voting> getVotingList(int page) {
		return votingDao.getList(page);
	}
	public Voting getVoting(String id) {
		return votingDao.getVoting(id);
	}

	public Voting getVotingPrev(String id) {
		return votingDao.getVotingPrev(id);
	}

	public Voting getVotingNext(String id) {
		return votingDao.getVotingNext(id);
	}
	
	public int insert(Voting voting) {
		 votingDao.insert(voting);
		 return 0;
	}

	public String getVotingNextId() {
		// TODO Auto-generated method stub
		return null;
	}

	public int edit(Voting voting) {
		votingDao.edit(voting);
		return 0;
	}

}
