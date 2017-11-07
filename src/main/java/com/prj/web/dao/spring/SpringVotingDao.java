package com.prj.web.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.web.dao.FreeDao;
import com.prj.web.dao.VotingDao;
import com.prj.web.entity.Free;
import com.prj.web.entity.Voting;

public class SpringVotingDao implements VotingDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Voting> getList(int page) {
		String sql = "select * from Voting limit ?,10";

		List<Voting> list = template.query(sql, new Object[] { (page - 1) * 10 },
				BeanPropertyRowMapper.newInstance(Voting.class));

		return list;
	}

	@Override
	public Voting getVoting(String id) {
		String sql = "select * from Voting where id = ?";

		Voting voting = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Voting.class));
		return voting;
	}

	@Override
	public Voting getVotingPrev(String id) {
		String sql = "select * from Voting where id < CAST(? as UNSIGNED) order by date DESC limit 1";

		Voting prev = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Voting.class));
		return prev;
	}

	@Override
	public Voting getVotingNext(String id) {
		String sql = "select * from Voting where id > CAST(? as UNSIGNED) order by date ASC limit 1";

		Voting next = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Voting.class));
		return next;
	}

	@Override
	public int insert(Voting voting) {
		String sql = "insert into Voting(id, title, content, writer_id) values(?, ?, ?, ?);";
		
		int result = template.update(sql, 
						getNextId(), 
						voting.getTitle(), 
						voting.getContent(), 
						voting.getWriterId());	
	
		return result;
	}

	private String getNextId() {
		String sql = "select ifnull(MAX(CAST(id as unsigned)),0)+1 from Voting";
		
		String nextId = template.queryForObject(sql, String.class);
		System.out.println(nextId);
		
		return nextId;
	}

	@Override
	public int edit(Voting voting) {
		String sql ="UPDATE Voting SET title = '?',content='?' WHERE id='?'";	
		
		int result = template.update(sql, 
				voting.getTitle(),
				voting.getContent(), 
				voting.getId());

		return result;
	}

}
