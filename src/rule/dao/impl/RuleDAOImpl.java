package rule.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import rule.bean.Rule;
import rule.dao.RuleDAO;
import rule.dao.RuleRowMapper;

public class RuleDAOImpl implements RuleDAO {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addRule(Rule rule) {
		// TODO Auto-generated method stub
		String sql = "insert into t_rule(rule_name,key_word,is_word_reply,word_content,is_picture_reply,picture_content,is_voice_reply,voice_content,is_video_reply,video_content,is_picture_word_reply,picture_content1,word_content1) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{rule.getRuleName(),rule.getKeyWord(),rule.getIsWordReply(),rule.getWordContent(),rule.getIsPictureReply(),rule.getPictureContent(),rule.getIsVoiceReply(),rule.getVoiceContent(),rule.getIsVideoReply(),rule.getVideoContent(),rule.getIsPictureWordReply(),rule.getPictureContent1(),rule.getWordContent1()});
	}

	@Override
	public void deleteRule(Rule rule) {
		// TODO Auto-generated method stub
		String sql = "delete from t_rule where ruleId = ?";
		jdbcTemplate.update(sql, new Object[]{rule.getRuleId()});
	}

	@Override
	public void updateRule(Rule rule) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rule getRuleByRuleID(int ruleId) {
		// TODO Auto-generated method stub
		Rule rule = (Rule) jdbcTemplate.queryForObject("select * from t_rule where ruleId=?", new Object[]{ruleId},new RuleRowMapper());
		return rule;
	}

	@Override
	public List<Rule> getRules() {
		// TODO Auto-generated method stub
		List<Rule> rules = jdbcTemplate.query("select * from t_rule", new RuleRowMapper());
		return rules;
	}

}
