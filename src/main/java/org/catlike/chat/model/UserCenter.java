package org.catlike.chat.model;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于存储所有已经上线的用户session
 * @author Administrator
 *
 */
public class UserCenter {
	
	private Map<String,Object> users=new HashMap<String,Object>();
	
	public static UserCenter userCenter=new UserCenter();
	
	private UserCenter(){
		
	}
	
	/**
	 * 将用户加入聊天中心
	 * @param userName
	 * @param session
	 */
	public void addSession(String userName, Session session){
		this.users.put(userName,session);
	}

	public Map<String, Object> getUsers() {
		return users;
	}
	
	public Session getUser(String userName) {
		return (Session) this.users.get(userName);
	}

	public void setUsers(Map<String, Object> users) {
		this.users = users;
	}
	
}
