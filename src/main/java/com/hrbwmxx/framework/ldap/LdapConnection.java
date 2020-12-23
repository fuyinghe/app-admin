package com.hrbwmxx.framework.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import org.apache.log4j.Logger;

import com.hrbwmxx.framework.ldap.util.CryptUtils;

public class LdapConnection {
	
  private Logger log = Logger.getLogger(LdapConnection.class);

  public Context getConnection()
    throws NamingException
  {
    Context context = null;
    String context_factory = "com.sun.jndi.ldap.LdapCtxFactory";
    String ldapURL = "ldap://192.168.8.185:389";
    String dn = "uid=impUser,ou=people,dc=hrbu,dc=edu,dc=cn";
    String password = new CryptUtils().decPassword("sxJvHde/sMY=");
    String auth_type = "simple";
    if ((ldapURL != null) && (dn != null) && (password != null) && (auth_type != null)) {
      Hashtable ht = new Hashtable();
      ht.put("java.naming.factory.initial", context_factory);
      ht.put("java.naming.provider.url", ldapURL);
      ht.put("java.naming.security.principal", dn);
      ht.put("java.naming.security.credentials", password);
      ht.put("java.naming.authoritative", auth_type);
      try {
        context = new InitialDirContext(ht);
        this.log.info("ldap连接成功");
      } catch (NamingException e) {
        this.log.error("********ldap连接出现异常 " + e.getMessage());
      }
    }
    else {
      this.log.debug("ldap连接失败,连接属性中有null值");
    }
    return context;
  }

  public InitialDirContext getConnection(String ldapIp, String ldapPort, String ldapUser, String ldapPassword)
    throws NamingException
  {
    InitialDirContext context = null;
    String context_factory = "com.sun.jndi.ldap.LdapCtxFactory";
    String ldapURL = "ldap://" + ldapIp + ":" + ldapPort;
    String dn = ldapUser;
    String password = ldapPassword;
    String auth_type = "simple";
    if ((ldapURL != null) && (dn != null) && (password != null) && (auth_type != null)) {
      Hashtable ht = new Hashtable();
      ht.put("java.naming.factory.initial", context_factory);
      ht.put("java.naming.provider.url", ldapURL);
      ht.put("java.naming.security.principal", dn);
      ht.put("java.naming.security.credentials", password);
      ht.put("java.naming.authoritative", auth_type);
      try {
        context = new InitialDirContext(ht);
        this.log.info("ldap连接测试成功");
      } catch (NamingException e) {
        this.log.error("ldap连接测试出现异常 ");
        throw new NamingException("ldap连接测试出现异常" + e.getMessage());
      }
    }
    return context;
  }
  
}