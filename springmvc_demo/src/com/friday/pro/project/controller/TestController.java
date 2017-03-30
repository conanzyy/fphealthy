package com.friday.pro.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.friday.pro.project.model.TestUser;
import com.friday.pro.project.service.ITestService;

/**
 * spring控制器
 * 在DispatcherServlet将请求分发给Spring Controller之前
 * 借助于Spring提供的HandlerMapping定位到具体的Controller
 * @author lxie
 * **/

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@Resource
	private ITestService iTestService;
	private Map<String,TestUser> users = new HashMap();
	
	public TestController() {
		users.put("lxie", new TestUser("lxie", "谢磊", "1234", "lxie@friday.com"));
		users.put("xiaoxinwl", new TestUser("xiaoxinwl", "王磊", "1234", "xiaoxinwl@friday.com"));
		users.put("aileen", new TestUser("aileen", "张敏", "1234", "aileen@friday.com"));
	}
	
	/**
	 * 用户列表
	 * @author lxie
	 */
	@RequestMapping(value="/userList",method=RequestMethod.GET)
	public String userList(Model model){
		Map sp_m = new HashMap();
		try {
			//通过数据库获取信息
			List<Map> list = iTestService.test();
			for(int i=0;i<list.size();i++){
				Map mp = list.get(i);
				System.out.println(mp.get("userId").toString()+"==="+mp.get("userName").toString()+"===");
			}
			model.addAttribute("users",users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/test/userList";
	}
	
	/**
	 * 初始化添加用户页
	 * @author lxie
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String insertUser(@ModelAttribute("user") TestUser user){
		return "/test/newUser";
	}
	
	/**
	 * 添加用户
	 * @author lxie
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String insertUser(@ModelAttribute("user") TestUser user,BindingResult result){
		users.put(user.getUserId(), user);
		return "redirect:/test/userList";
	}
	
	/**
	 * 初始化更新用户页
	 * @author lxie
	 */
	@RequestMapping(value="/{userId}/update",method=RequestMethod.GET)
	public String updateUser(@PathVariable String userId,@ModelAttribute("user") TestUser user){
		user = users.get(userId);
		return "/test/updateUser";
	}
	
	/**
	 * 更新用户
	 * @author lxie
	 */
	@RequestMapping(value="/{userId}/update",method=RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") TestUser user,BindingResult result,@PathVariable String userId){
		users.remove(userId);
		users.put(user.getUserId(), user);
		return "redirect:/test/userList";
	}
	
	/**
	 * 删除用户
	 * @author lxie
	 */
	@RequestMapping(value="/{userId}/delete",method=RequestMethod.GET)
	public String delUser(@PathVariable String userId,Model model){
		users.remove(userId);
		return "redirect:/test/userList";
	}
	
	
	
	
	
	
	
	
	
	
}
