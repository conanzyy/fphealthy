package rule.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import rule.bean.Rule;
import rule.service.RuleService;

@Controller
public class RuleControl {
	private RuleService ruleService;

	@Autowired
	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}
	
	@RequestMapping(value = "/rules")
	public ModelAndView doLoginIn(HttpServletRequest req,
			HttpServletResponse res, HttpSession sess) {
		List<Rule> rules = ruleService.getRules();
		return new ModelAndView("/rule/rule").addObject("rules", rules);
	}
	
	@RequestMapping(value = "/addRule")
	public ModelAndView addRule(HttpServletRequest req,
			HttpServletResponse res,@RequestParam MultipartFile[] file) throws IOException{
		String ruleName = req.getParameter("ruleName");
		String keyWord = req.getParameter("keyWord");
		String wordContent = req.getParameter("wordContent");
		String wordContent1 = req.getParameter("wordContent1");
		Rule rule = new Rule();
		rule.setRuleName(ruleName);
		rule.setKeyWord(keyWord);
		rule.setWordContent(wordContent);
		rule.setWordContent1(wordContent1);
		String[] replyType = req.getParameterValues("reply");
		//0:文字,1:图片,2:声音,3:视频,4:图文,
		int fileIndex = 0;
		List<String> fileNameList = new ArrayList<String>();
		for(String type:replyType)
		{
			if("0".equals(type))
			{
				rule.setIsWordReply(1);
			}
			else if("1".equals(type))
			{
				rule.setIsPictureReply(1);
				MultipartFile mfile = file[0];
				String fileFormat = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
				String fileName = System.currentTimeMillis()+fileFormat;
				fileNameList.add(fileName);
				rule.setPictureContent(fileName);
			}
			else if("2".equals(type))
			{
				rule.setIsVoiceReply(1);
				MultipartFile mfile = file[1];
				String fileFormat1 = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
				String fileName1 = System.currentTimeMillis()+fileFormat1;
				fileNameList.add(fileName1);
				rule.setVoiceContent(fileName1);
			}
			else if("3".equals(type))
			{
				rule.setIsVideoReply(1);
				MultipartFile mfile = file[2];
				String fileFormat2 = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
				String fileName2 = System.currentTimeMillis()+fileFormat2;
				fileNameList.add(fileName2);
				rule.setVoiceContent(fileName2);
			}
			else if("4".equals(type))
			{
				rule.setIsPictureWordReply(1);
				MultipartFile mfile = file[3];
				String fileFormat3 = mfile.getOriginalFilename().substring(mfile.getOriginalFilename().lastIndexOf("."));
				String fileName3 = System.currentTimeMillis()+fileFormat3;
				fileNameList.add(fileName3);
				rule.setVoiceContent(fileName3);
			}
		}
		int fileNameListIndex = 0;
		for(MultipartFile mfile : file)
		{
			if(!mfile.isEmpty())
			{
				String realPath = req.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(mfile.getInputStream(), new File(realPath, fileNameList.get(fileNameListIndex++)));
			}
		}
		ruleService.addRule(rule);
		return new ModelAndView("redirect:/rules.do");
	}
}
