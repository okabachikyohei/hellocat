package org.okabachi.hellocat.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.okabachi.hellocat.dao.IUserDao;
import org.okabachi.hellocat.entity.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IUserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int addUser(IUser user) {
		int userId = (Integer)sessionFactory.getCurrentSession().save(user);
		return userId;
	}

	@SuppressWarnings("unchecked")
	public List<IUser> listUser() {
		return sessionFactory.getCurrentSession().createQuery("from IUser").list();
	}

	public void removeUser(Integer userId) {
		IUser user = (IUser) sessionFactory.getCurrentSession().load(IUser.class, userId);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}
	}

	public void updateUser(IUser user) {
		sessionFactory.getCurrentSession().merge(user);
	}

	public IUser getUser(Integer userId) {
		IUser user = (IUser) sessionFactory.getCurrentSession().get(IUser.class, userId);    	
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<IUser> findUserByUseridAndPassword(String userid, String password) {
		Query query = sessionFactory.getCurrentSession().createQuery("from IUser as u where u.user_id = ? and u.password = ?");
		query.setString(0, userid);
		query.setString(1, password);
		List<IUser> userList = query.list();
		return userList;
	}

	public IUser getUserByUserName(String userName) {		
		Query query = sessionFactory.getCurrentSession().createQuery("from User as u where u.userid = ?");
		query.setString(0, userName);
		@SuppressWarnings("unchecked")
		List<IUser> userList = query.list();
		return userList.get(0);
	}

}

