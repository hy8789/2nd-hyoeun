package com.prj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prj.web.entity.Free;
import com.prj.web.entity.Voting;

public interface VotingDao {
	List<Voting> getList(@Param("page") int page);
	Voting getVoting(String id);
	Voting getVotingPrev(String id);
	Voting getVotingNext(String id);
	int insert(Voting voting);
	int edit(Voting voting);
	
}
