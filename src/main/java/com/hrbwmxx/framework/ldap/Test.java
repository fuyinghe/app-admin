package com.hrbwmxx.framework.ldap;

public class Test {

	public static void main(String[] args) throws Exception {

		LdapHandlerDAO dao = new LdapHandlerDAO();
		dao.initLdapConn();
		
		dao.ModifyUser("20100008", "22690426");
	}

}
