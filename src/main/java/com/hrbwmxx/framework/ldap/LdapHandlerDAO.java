package com.hrbwmxx.framework.ldap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;

public class LdapHandlerDAO {
  private LdapConnection ldapConn;
  private Logger log = Logger.getLogger(LdapHandlerDAO.class);
  private InitialDirContext context = null;
  private InitialDirContext contextRemote = null;
  private String dn="dc=hrbu,dc=edu,dc=cn";

  public void initLdapConn() {
    this.log.info("开始初使化ldap连接");
    try {
    	ldapConn = new LdapConnection();
      this.context = ((InitialDirContext)this.ldapConn.getConnection());
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 修改密码
   * @param uid
   * @param password
   * @return
   * @throws Exception
   */
  public boolean ModifyUser(String uid,String password) throws Exception {  
      boolean result = true;  
      String userDN = "uid=" + uid + "," + "ou=people,"+dn;  
      Attributes attrs = new BasicAttributes(true);
      attrs.put("userPassword", password);
      this.context.modifyAttributes(userDN, DirContext.REPLACE_ATTRIBUTE, attrs);  
      this.context.close();
      return result;  
  }
  
  
}