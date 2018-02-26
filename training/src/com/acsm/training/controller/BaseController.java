package com.acsm.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.acsm.training.dao.EmployeeNewDao;
import com.acsm.training.model.*;
import com.acsm.training.service.*;
import com.acsm.training.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseController {
	
	@Resource
	private TokenService tokenService;
	
	@Resource
	private UserService userService;

	@Autowired
	private BoxService boxService;

	@Autowired
	private CoachService coachService;

	@Autowired
	private EmployeeNewDao employeeNewDao;

	@Autowired
	private MemberService memberService;

	/**
	 * 获取登录用户
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request){
		User user=null;
		user=(User)request.getSession().getAttribute("currentUser");
		return user;
	}

	/**
	 * 校验token
	 * @param tokenKey
	 * @return
	 */
	public User validateToken(String tokenKey){
		Token token = tokenService.queryByTokenKey(tokenKey);
		if(token!=null){
			User user = userService.queryById(token.getUserId());
			return user;
		}
		return null;
	}

	/**
	 *查询当前登录用户的老板
	 * @param userId
	 * @return
	 */
	public User getLoginUserOfBoss(int userId){
		//如果返回user_type = 1 ,则就是老板
		User loginUsser = userService.queryById(userId);
		if(loginUsser.getUserType() == 1){
			return loginUsser;
		}else{
			//如果不是老板类型的用户，则查询该员工所属馆，管的user_id就是老板id
			if(loginUsser.getUserType() == UserType.MEMBER.CODE){ //会员
				Box box = boxService.queryById(memberService.queryById(loginUsser.getRelatedId()).getBoxId());
				return userService.queryById(box.getUserId());
			}else if(loginUsser.getUserType() == UserType.EMPLOYEE.CODE){ //员工
				Box box = boxService.queryById(employeeNewDao.queryById(loginUsser.getRelatedId()).getBoxId());
				return userService.queryById(box.getUserId());
			}else{
				//教练
				Box box = boxService.queryById(loginUsser.getBoxId());
				return userService.queryById(box.getUserId());
			}

		}
	}
	
	public User getLoginUserOfBoss(int userId,int boxId){
		//如果返回user_type = 1 ,则就是老板
		User loginUsser = userService.queryById(userId);
		if(loginUsser.getUserType() == 1){
			return loginUsser;
		}else{//如果不是老板类型的用户，则查询该员工所属馆，管的user_id就是老板id
			Box box = boxService.queryById(boxId);
			return userService.queryById(box.getUserId());
		}
	}

	/**
	 * 获取当前登录用户的馆
	 * 1:老板，所有的馆
	 * 3:教练，自己的馆
	 * @param request
	 * @return
	 */
	public List<Box> getBoxListOfUser(HttpServletRequest request){
		User loginUser = getUser(request);
		User boss = getLoginUserOfBoss(loginUser.getId());
		List<Box> boxList = new ArrayList<>();
		if(loginUser.getUserType() == UserType.BOSS.getCODE()){
			boxList = boxService.queryBoxListOfUser(boss.getId());
		}else if(loginUser.getUserType() == UserType.COACH.getCODE()){
			Coach coach = coachService.queryById(loginUser.getRelatedId());
			Box box = boxService.queryById(coach.getBoxId());
			boxList.add(box);
		}else if(loginUser.getUserType() == UserType.EMPLOYEE.getCODE()){
			EmployeeNew employee = employeeNewDao.queryById(loginUser.getRelatedId());
			Box box = boxService.queryById(employee.getBoxId());
			boxList.add(box);
		}
		return boxList;
	}


	public Integer getBossIdOfLoginUser(HttpServletRequest request){
		User loginUsser = (User)request.getSession().getAttribute("currentUser");
		if(loginUsser.getUserType() == 1){
			return loginUsser.getId();
		}else{//如果不是老板类型的用户，则查询该员工所属馆，管的user_id就是老板id
			Box box = boxService.queryById(loginUsser.getBoxId());
			return userService.queryById(box.getUserId()).getId();
		}
	}
}
