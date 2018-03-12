package com.github.wolfclub.oneblog.controller;

import com.github.wolfclub.oneblog.commons.util.Assert;
import com.github.wolfclub.oneblog.exception.ServiceException;
import com.github.wolfclub.oneblog.exception.VersionConflictException;
import com.github.wolfclub.oneblog.po.User;
import com.github.wolfclub.oneblog.service.BaseEntityService;
import com.github.wolfclub.oneblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuchan
 * @date 11:42 2018/2/27
 */
@Controller
@RequestMapping(value="user")
public class UserController extends BaseEntityController<User>{

	@Autowired
	private UserService userService;

	@Override
	protected BaseEntityService<User> getBaseEntityService() {
		return userService;
	}


	@RequestMapping(value="/delete")
	@ResponseBody
	public void delete(@RequestParam("id") Long id, @RequestParam(value = "version", defaultValue = "-1") long version) throws ServiceException, VersionConflictException {
		Assert.assertArgumentNotNull(id, "id");

		User user = new User();
		user.setId(id);
		user.setVersion(version);
		System.out.println("delete 请求");
		int i = getBaseEntityService().delete(user, getOperateContext());
	}


}
