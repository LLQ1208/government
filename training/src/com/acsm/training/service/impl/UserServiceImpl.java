package com.acsm.training.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.model.Menu;
import com.acsm.training.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.enums.MenuEnum;
import com.acsm.training.enums.UserType;
import com.acsm.training.util.UserRegisterValidateUtil;
import com.acsm.training.vo.CommonResult;
import com.acsm.training.vo.MenuVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="boxDao")
	private BoxDao boxDao;

	@Resource
	private CoachMenuService coachMenuService;

	@Resource
	private RoleMenuDao roleMenuDao;

	@Resource
	private RoleEmployeeDao roleEmployeeDao;

	@Resource
	private RoleService roleService;

	@Resource
	private MemberDao memberDao;

	@Resource
	private RoleEmployeeMappingService roleEmployeeMappingService;

	@Resource
	private RoleNewMenuDao roleNewMenuDao;

	@Resource
	private UserService userService;

	@Autowired
	private BoxService boxService;
	@Autowired
	private CoachService coachService;
	@Autowired
	private EmployeeNewService employeeNewService;


	private boolean getBoxExists(Integer userId){
		//如果返回user_type = 1 ,则就是老板
		User loginUsser = userService.queryById(userId);
		if(loginUsser.getUserType() == 1){
			List<Box> boxArr = boxService.queryBoxListOfUser(loginUsser.getId());
			if(boxArr.size() == 0){
				return false;
			}
		}else{//如果不是老板类型的用户，
			if(null == loginUsser.getBoxId()){
				return false;
			}
		}
		return true;
	}

	@Override
	public CommonResult login(HttpSession session, String userName, String password) {
		User user = userDao.queryUserByUserName(userName);
		String sha1Pwd = UserRegisterValidateUtil.encodePassword(password,"SHA");
		boolean hasWod = true;
		if(user!=null && sha1Pwd.equals(user.getPassword())){
			if(user.getUserType() == UserType.BOSS.getCODE() || user.getUserType() == UserType.COACH.getCODE() || user.getUserType() == UserType.EMPLOYEE.getCODE()){
				String loginShowName = user.getUserName();
				if(getBoxExists(user.getId())){
					session.setAttribute("backCan","0");
					if(user.getUserType() == UserType.BOSS.getCODE()){
						if(user.getIco() != null ){
							session.setAttribute("loginImg",user.getIco());
						}else{
							session.setAttribute("loginImg","");
						}
						session.setAttribute("backCan","1");
					}
					if(user.getUserType() == UserType.COACH.getCODE()){
						Coach coach = coachService.queryById(user.getRelatedId());
						session.setAttribute("loginImg",coach.getAvatar());
						session.setAttribute("backCan","1");
						loginShowName = coach.getName();
					}
					if(user.getUserType() == UserType.EMPLOYEE.getCODE()){
						EmployeeNew emp = employeeNewService.queryById(user.getRelatedId());
						session.setAttribute("loginImg",emp.getLogo());
						loginShowName = emp.getName();
					}
				}else{
					if(user.getUserType() == UserType.BOSS.getCODE()){
						return new CommonResult(3, "无训练馆", true,hasWod);
					}else{
						return new CommonResult(4, "登录信息有误", false,hasWod);
					}

				}
//				if(null == user.getBoxId()){
//					session.setAttribute("currentUser", user);
//					return new CommonResult(3, "success", true);
//				}
//				Box box = boxDao.queryById(user.getBoxId());
//				if(box.getStatus() == 0){
//					return new CommonResult(0, "训练馆已停用", false);
//				}

				User loginUsser = userService.queryById(user.getId());
				if(loginUsser.getUserType() == 1){
					List<Box> boxArr = boxService.queryBoxListOfUser(loginUsser.getId());
					if(boxArr.size() > 0){
						user.setBox(boxArr.get(0));
					}else{
						return new CommonResult(0, "训练馆已停用", false,hasWod);
					}
				}else{
					user.setBox(boxService.queryById(loginUsser.getBoxId()));
				}
				session.setAttribute("currentUser", user);
				session.setAttribute("loginShowName",loginShowName);
				if(user.getUserType() != UserType.BOSS.CODE){
					MenuNewModel menuNewModel = getMenu(user.getId());
					if(!menuNewModel.isCALENDAR_MENU()){
						hasWod = false;
					}
					if(null != menuNewModel){
						session.setAttribute("menuNew", menuNewModel);
					}
					session.setAttribute("boss",false);
				}
				if(user.getUserType()== UserType.BOSS.CODE){
					//老板有所有权限
//				MenuVO menuVO = buildBossMenu();
//				session.setAttribute("menuVO", menuVO);
					session.setAttribute("boss",true);
				}else if(user.getUserType()==UserType.EMPLOYEE.CODE){
					//员工
//				Role role = roleEmployeeDao.queryRoleByEmployeeId(user.getRelatedId());
//				List<Menu> menus = roleMenuDao.queryMenuListByRoleId(role.getId());
//				Map<Integer, Menu> roleMenuMap = roleService.convertMenuMap(menus);
////				MenuVO menuVO = buildEmployeeMenu(roleMenuMap);
				}else if(user.getUserType()==UserType.COACH.CODE){
					//教练
//				Map<Integer, Menu> coachMenuMap = coachMenuService.queryCoachMenuMapByBoxId(user.getBoxId());
//				MenuVO menuVO = buildEmployeeMenu(coachMenuMap);
//				session.setAttribute("menuVO", menuVO);
				}
				return new CommonResult(1, "success", true,hasWod);
			}else if(user.getUserType() == UserType.MEMBER.getCODE()){
				return new CommonResult(2, "您不是训练馆工作人员", false,hasWod);
			}
		}
		return new CommonResult(2, "用户名或密码不正确", false,hasWod);
	}

	private MenuVO buildEmployeeMenu(Map<Integer, Menu> roleMenuMap) {
		MenuVO menuVO = new MenuVO();
		if(roleMenuMap.get(MenuEnum.member.ID)!=null) {
			menuVO.setMember(true);
		}
		if(roleMenuMap.get(MenuEnum.coach.ID)!=null) {
			menuVO.setCoach(true);
		}
		if(roleMenuMap.get(MenuEnum.config.ID)!=null) {
			menuVO.setConfig(true);
		}
		if(roleMenuMap.get(MenuEnum.courseContent.ID)!=null) {
			menuVO.setCourseContent(true);
		}
		if(roleMenuMap.get(MenuEnum.courseOrder.ID)!=null) {
			menuVO.setCourseOrder(true);
		}
		if(roleMenuMap.get(MenuEnum.coursePlan.ID)!=null) {
			menuVO.setCoursePlan(true);
		}
		if(roleMenuMap.get(MenuEnum.notification.ID)!=null) {
			menuVO.setNotification(true);
		}
		if(roleMenuMap.get(MenuEnum.role.ID)!=null) {
			menuVO.setRole(true);
		}
		if(roleMenuMap.get(MenuEnum.supporter.ID)!=null) {
			menuVO.setSupporter(true);
		}
		if(roleMenuMap.get(MenuEnum.courseType.ID)!=null) {
			menuVO.setCourseType(true);
		}
		return menuVO;
	}

	public MenuVO buildBossMenu(){
		MenuVO menuVO = new MenuVO();
		menuVO.setMember(true);
		menuVO.setCoach(true);
		menuVO.setConfig(true);
		menuVO.setCourseContent(true);
		menuVO.setCourseOrder(true);
		menuVO.setCoursePlan(true);
		menuVO.setNotification(false);
		menuVO.setRole(true);
		menuVO.setSupporter(true);
		menuVO.setCourseType(true);
		return menuVO;
	}

	@Override
	public User queryUserByRelatedIdAndUserType(int relatedId,int userType) {
		return userDao.queryUserByRelatedIdAndUserType(relatedId, userType);
	}

	@Override
	public User queryById(Integer id) {
		User user = userDao.queryById(id);
		if(user!=null){
			Box box = boxDao.queryById(user.getBoxId());
			user.setBox(box);
		}
		return user;
	}

	@Override
	public User login(String userName, String password) {
		User user = userDao.queryUserByUserName(userName);
		if(user!=null){
			if(password.equals(user.getPassword())){
				Box box = boxDao.queryById(user.getBoxId());
				user.setBox(box);
				return user;
			}else{
				return null;
			}
		}
		return null;
	}

	@Override
	public void deleteByRelatedIdAndUserType(Integer relatedId, Integer userType) {
		User user = userDao.queryUserByRelatedIdAndUserType(relatedId, userType);
		user.setIsDeleted(1);
		userDao.update(user);
	}

	@Override
	public User queryByUserName(String userName) {
		return userDao.queryUserByUserName(userName);
	}

	@Override
	public User queryByUserName(String userName, String oldUserName) {
		return userDao.queryUserByUserName(userName, oldUserName);
	}

	@Override
	public User loginForPhone(String phone, String password) {
		User user = userDao.queryUserByPhone(phone);
		if(user==null){
			return null;
		}
		if(!password.equals(user.getPassword())){
			return null;
		}
		if(user.getBoxId() != null) {
			Box box = boxDao.queryById(user.getBoxId());
			user.setBox(box);
		}
		return user;
	}


	@Override
	public boolean addRegister(String phone, String password) {
		User user = new User();
		Member member = new Member();
		member.setPhone(phone);
		member.setCreateTime(new Date());
		member.setStatus(1);
		member.setType(1);
		Member saveMember = memberDao.add(member);
		user.setUserName(phone);
		user.setPassword(password);
		user.setUserType(UserType.MEMBER.CODE);
		user.setRelatedId(saveMember.getId());
		user.setIsDeleted(0);
		userDao.add(user);
		return true;
	}

	/**
	 * 更新用户信息
	 *
	 * @param user
	 */
	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void saveUser(User user) {
		userDao.add(user);
	}

	@Override
	public boolean queryPhoneIsExist(String phone) {
		List<User> userList = userDao.queryUserPhone(phone);
		if(null == userList || userList.size() < 1){
			return false;
		}
		return true;
	}

	@Override
	public User queryPhone(String phone) {
		List<User> userList = userDao.queryUserPhone(phone);
		if(null == userList || userList.size() < 1){
			return null;
		}
		return userList.get(0);
	}

	private MenuNewModel getMenu(Integer userId){
		MenuNewModel menuNewModel = new MenuNewModel();
		User user = userDao.queryById(userId);
		List<RoleMenuNew> roleMenuNewsList = new ArrayList<RoleMenuNew>();
		//员工权限
		if(user.getUserType() != UserType.COACH.CODE && user.getUserType() != UserType.BOSS.CODE){
			RoleEmployeeMapping roleEmployeeMapping = roleEmployeeMappingService.findById(user.getRelatedId(),user.getUserType());
			roleMenuNewsList = roleNewMenuDao.queryListByRoleId(roleEmployeeMapping.getRoleId());
		}else if(user.getUserType() == UserType.COACH.CODE && user.getUserType() != UserType.BOSS.CODE){  //教练角色的ID
			RoleEmployeeMapping roleEmployeeMapping = roleEmployeeMappingService.findById(user.getRelatedId(),user.getUserType());
			roleMenuNewsList = roleNewMenuDao.queryListByRoleId(roleEmployeeMapping.getRoleId());
		}

		for (RoleMenuNew roleMenuNew : roleMenuNewsList) {
			switch (roleMenuNew.getMenuId()){ //对应的case数字，参照t_menu_new的ID
				case 10:
					menuNewModel.setWOD_MENU(true);
					break;
				case  11:
					menuNewModel.setCALENDAR_MENU(true);
					break;
				case  12:
					menuNewModel.setACTION_MENU(true);
					break;
				case 13:
					menuNewModel.setWOD_SETTING_MENU(true);
					break;
				case  16:
					menuNewModel.setOPERATION_MENU(true);
					break;
				case  17:
					menuNewModel.setMEMBER_ANALYSIS_MENU(true);
					break;
				case  18:
					menuNewModel.setCOACH_ANALYSIS_MENU(true);
					break;
				case  19:
					menuNewModel.setMEMBER_MANAGE_TOP_MENU(true);
					break;
				case  20:
					menuNewModel.setMEMBER_MANAGE_MENU(true);
					break;
				case  21:
					menuNewModel.setTEMPLATE_MENU(true);
					break;
				case  22:
					menuNewModel.setAPPOINTMENT_TOP_MENU(true);
					break;
				case  23:
					menuNewModel.setAPPOINTMENT_MENU(true);
					break;
				case  24:
					menuNewModel.setCOURSE_MENU(true);
					break;
				case  25:
					menuNewModel.setCOURSE_CALENDAR_MENU(true);
					break;
				case  26:
					menuNewModel.setCOURSE_COACH_MENU(true);
					break;
				case  27:
					menuNewModel.setMANAGE_MENU(true);
					break;
				case  28:
					menuNewModel.setCOURSE_TYPE_MENU(true);
					break;
				case  29:
					menuNewModel.setMAIL_MANAGE(true);
					break;
				case  30:
					menuNewModel.setMAIL_LIST(true);
					break;
				case  50:
					menuNewModel.setMANAGE_TOP_MENU(true);
					break;
				case  51:
					menuNewModel.setMANAGE_ROLE_MENU(true);
					break;
				case  52:
					menuNewModel.setMANAGE_EMPLOYEE_MENU(true);
					break;
			}
		}
		return menuNewModel;
	}
}
