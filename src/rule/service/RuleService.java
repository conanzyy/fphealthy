package rule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rule.bean.Rule;
import rule.dao.RuleDAO;

@Service
public class RuleService {
	private RuleDAO ruleDAO;

	@Autowired
	public void setRuleDAO(RuleDAO ruleDAO) {
		this.ruleDAO = ruleDAO;
	}
	
	public void addRule(Rule rule)
	{
		ruleDAO.addRule(rule);
	}
	
	public Rule getRuleByRuleID(int ruleId)
	{
		Rule rule = ruleDAO.getRuleByRuleID(ruleId);
		return rule;
	}
	
	public List<Rule> getRules()
	{
		List<Rule> rules = ruleDAO.getRules();
		return rules;
	}
}
