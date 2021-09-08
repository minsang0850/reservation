package kr.or.connect.reservation.dao;

public class UserRoleDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT mr.id, mr.user_id, mr.role_name FROM user_role mr JOIN user m ON mr.user_id = m.id WHERE m.email = :email";
}