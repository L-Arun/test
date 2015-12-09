package com.itomatoo.team.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.itomatoo.team.bean.PageBean;
import com.itomatoo.team.dao.UserDao;
import com.itomatoo.team.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void merge(User user) {
		getHibernateTemplate().merge(user);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<User> list(final String userName, final String name, final Date beginDate,
			 final Date endDate, final Long roleID, final String valid, final PageBean pageBean) {
		return (List<User>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select u,r from User u,Role r where 1 = 1");
						hql.append(" and u.roleID = r.id");
						
						if(userName != null && !"".equals(userName)){//用户名查询
							hql.append(" and u.userName like :userName");
						}
						if(name != null && !"".equals(name)){//真实姓名查询
							hql.append(" and u.name like :name");
						}
						if(beginDate != null){//起始创建时间查询
							hql.append(" and u.createTime >= :beginDate");
						}
						if(endDate != null){//终止创建时间查询
							hql.append(" and u.createTime < :endDate");
						}
						if (roleID != null && roleID != -1L) {//角色编号查询
							hql.append(" and u.roleID = :roleID");//添加角色编号查询条件
						}
						if(valid != null && !"".equals(valid)){//是否有效查询
							hql.append(" and u.valid= :valid");
						}
						Query query = session.createQuery(hql.toString());
								
						if(userName != null && !"".equals(userName)){
							query.setParameter("userName", "%" + userName + "%");
						}
						if(name != null && !"".equals(name)){
							query.setParameter("name", "%" + name + "%");
						}
						if(beginDate != null){
							query.setParameter("beginDate", beginDate);
						}
						if(endDate != null){
							query.setParameter("endDate", endDate);
						}
						if (roleID != null && roleID != -1L) {
							query.setParameter("roleID", roleID);//设置角色编号
						}
						if(valid != null && !"".equals(valid)){
							if("true".equals(valid)){
								query.setParameter("valid", true);
							}else{
								query.setParameter("valid", false);
							}
						}
						if(pageBean.isPageFlag()){
							if(pageBean.getPageSize() != 0){
								query.setFirstResult((pageBean.getPage() - 1) * pageBean.getPageSize());
								query.setMaxResults(pageBean.getPageSize());
							}
						}
						List<User> userList = new ArrayList<User>();
						List tempList = query.list();//查询以数组的形式返回
						Iterator iter = tempList.iterator();
						while(iter.hasNext()) {
							Object[] obj = (Object[])iter.next();
							User tempUser = (User)obj[0];//下标为0的是User对象
							userList.add(tempUser);
						}
						return userList;
					}
				});
	}

	public User get(Long ID) {
		return getHibernateTemplate().get(User.class, ID);
	}

	public void del(User user) {
		getHibernateTemplate().delete(user);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public User getByUserName(final String userName) {
		return (User) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String hql = "from User u where u.userName = :userName and u.valid=1";
						Query query = session.createQuery(hql);
						query.setParameter("userName", userName);
						List list = query.list();
						if (list != null && list.size() != 0) {
							return list.get(0);
						}
						return null;
					}
				});
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageBean getPageBean(final String userName, final String name, final Date beginDate,
			final Date endDate, final Long roleID, final String valid, final PageBean pageBean) {
		return (PageBean) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer hql = new StringBuffer("select count(u) from User u where 1 = 1");
						
						if(userName != null && !"".equals(userName)){
							hql.append(" and u.userName like :userName");
						}
						if(name != null && !"".equals(name)){
							hql.append(" and u.name like :name");
						}
						if(beginDate != null){
							hql.append(" and u.createTime >= :beginDate");
						}
						if(endDate != null){
							hql.append(" and u.createTime < :endDate");
						}
						if (roleID != null && roleID != -1L) {
							hql.append(" and u.roleID = :roleID");
						}
						if(valid != null && !"".equals(valid)){
							hql.append(" and u.valid=:valid");
						}
						Query query = session.createQuery(hql.toString());
								
						if(userName != null && !"".equals(userName)){
							query.setParameter("userName", "%" + userName + "%");
						}
						if(name != null && !"".equals(name)){
							query.setParameter("name", "%" + name + "%");
						}
						if(beginDate != null){
							query.setParameter("beginDate", beginDate);
						}
						if(endDate != null){
							query.setParameter("endDate", endDate);
						}
						if (roleID != null && roleID != -1L) {
							query.setParameter("roleID", roleID);
						}
						if(valid != null && !"".equals(valid)){
							if("true".equals(valid)){
								query.setParameter("valid", true);
							}else{
								query.setParameter("valid", false);
							}
						}
						if(pageBean.isPageFlag()){
							int totalCount = ((Long)query.iterate().next()).intValue();
							pageBean.setCount(totalCount);
							int pageCount = 0;//页数
							if(pageBean.getPageSize() != 0) {
					            pageCount = totalCount / pageBean.getPageSize();
					            if(totalCount % pageBean.getPageSize() != 0) {
					                pageCount ++;
					            }
					        }
							pageBean.setPageCount(pageCount);
						}
						return pageBean;
					}
				});
	}

}
