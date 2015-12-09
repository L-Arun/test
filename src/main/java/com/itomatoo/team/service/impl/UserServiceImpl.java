package com.itomatoo.team.service.impl;

import java.util.Date;
import java.util.List;

import com.itomatoo.team.bean.PageBean;
import com.itomatoo.team.dao.UserDao;
import com.itomatoo.team.model.User;
import com.itomatoo.team.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void manage(User user){
		userDao.merge(user);
	}


	/**
	 * 多条件分页查询用户
	 * @param userName	用户名
	 * @param name	姓名
	 * @param beginDate	起始创建时间
	 * @param endDate 结束创建时间
	 * @param roleID 角色编号
	 * @param valid	是否有效
	 * @param pageBean
	 * @return
	 */
	public List<User> list(String userName, String name, Date beginDate,
			Date endDate, Long roleID, String valid, PageBean pageBean) {
		return userDao.list(userName, name, beginDate, endDate, roleID, valid, pageBean);
	}

	/**
	 * 根据用户编号查询用户
	 * @param ID 用户编号
	 */
	public User get(Long ID) {
		return userDao.get(ID);
	}

	public void del(User user) {
		userDao.del(user);
	}

	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	/**
	 * 封装多条件查询分页信息
	 * @param userName	用户名
	 * @param name	姓名
	 * @param beginDate	起始创建时间
	 * @param endDate 结束创建时间
	 * @param roleID 角色编号
	 * @param valid	是否有效
	 * @param pageBean
	 * @return
	 */
	public PageBean getPageBean(String userName, String name, Date beginDate,
			Date endDate, Long roleID, String valid, PageBean pageBean) {
		return userDao.getPageBean(userName, name, beginDate, endDate, roleID, valid, pageBean);
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
