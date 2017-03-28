package rule.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rule.bean.Rule;

public class RuleRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Rule rule = new Rule();
		rule.setRuleId(Integer.valueOf(rs.getString("rule_id")));
		rule.setRuleName(rs.getString("rule_name"));
		rule.setKeyWord(rs.getString("key_word"));
		rule.setIsWordReply(Integer.valueOf(rs.getString("is_word_reply")));
		rule.setWordContent(rs.getString("word_content"));
		rule.setIsPictureReply(Integer.valueOf(rs.getString("is_picture_reply")));
		rule.setPictureContent(rs.getString("picture_content"));
		rule.setIsVoiceReply(Integer.valueOf(rs.getString("is_voice_reply")));
		rule.setVoiceContent(rs.getString("voice_content"));
		rule.setIsVideoReply(Integer.valueOf(rs.getString("is_video_reply")));
		rule.setVideoContent(rs.getString("video_content"));
		rule.setIsPictureWordReply(Integer.valueOf(rs.getString("is_picture_word_reply")));
		rule.setPictureContent1(rs.getString("picture_content1"));
		rule.setWordContent1(rs.getString("word_content1"));
		return rule;
	}

}
