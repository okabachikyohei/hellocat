package org.okabachi.hellocat.dao;

import java.util.List;

import org.okabachi.hellocat.entity.IUser;


public interface IUserDao {
	public int addUser(IUser user);
	public List<IUser> listUser();
	public void removeUser(Integer userId);
    public void updateUser(IUser user);
    public IUser getUser(Integer userId);
    public IUser getUserByUserName(String userName);
    
    public List<IUser> findUserByUseridAndPassword(String userid, String password);
}
