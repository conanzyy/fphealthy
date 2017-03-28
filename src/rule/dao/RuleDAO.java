package rule.dao;

import java.util.List;

import rule.bean.Rule;

public interface RuleDAO {
	public void addRule(Rule rule);
	public void deleteRule(Rule rule);
	public void updateRule(Rule rule);
	public Rule getRuleByRuleID(int ruleId);
	public List<Rule> getRules();
}
