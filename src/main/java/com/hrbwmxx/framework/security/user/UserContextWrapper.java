package com.hrbwmxx.framework.security.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hrbwmxx.framework.security.user.UserContext;
import com.hrbwmxx.framework.security.user.UserEntity;
import com.hrbwmxx.framework.util.PasswordEncoder;

public class UserContextWrapper implements Serializable, UserDetails, UserContext {

	private static final long serialVersionUID = -3514046146870184123L;
	
	private static final String FLAG_UNLOCK = "N";
	private static final String FLAG_ENABLED = "Y";
	private static final String FLAG_QUICK_LOGIN = "Y";
	private static final String FORMAT_PWD_SALT = "[!@{0}#$]";
	
	private UserEntity userEntity = null;
	
    public UserContextWrapper(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

	public Collection<GrantedAuthority> getAuthorities() {
		return null;
	}
	
	public UserEntity getUserDetail() {
		return userEntity;
	}

	public void setUserDetail(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getUsername() {
		return userEntity.getUsername();
	}
	
	public String getName() {
		return userEntity.getName();
	}
	
	public BigDecimal getJobNumber() {
		return userEntity.getJobNumber();
	}
	
	public BigDecimal getDeptId() {
		return userEntity.getDeptId();
	}
	
	public String getPassword() {
		if (FLAG_QUICK_LOGIN.equals(userEntity.getQuickLoginFlag())) {
			return PasswordEncoder.cryptoPassword(userEntity.getId(),userEntity.getPassword());
		} else {
			return userEntity.getPassword();
		}
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return FLAG_UNLOCK.equals(userEntity.getIsLockOut());
	}
	
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return FLAG_ENABLED.equals(userEntity.getActiveFlag()) && 
		FLAG_ENABLED.equals(userEntity.getRecActiveFlag());
	}
	
	public String getSalt() {
		
		return MessageFormat.format(FORMAT_PWD_SALT, userEntity.getId());
	}
	
	public BigDecimal getUserId() {
		return userEntity.getId();
	}

	
	public String getPicture() {
		return userEntity.getPicture();
	}

	
	public BigDecimal getPersonId() {
		// TODO Auto-generated method stub
		return userEntity.getPersonId();
	}

	
	public String getDeptName() {
		// TODO Auto-generated method stub
		return userEntity.getDeptName();
	}

	
	public String getUserIP() {
		// TODO Auto-generated method stub
		return userEntity.getUserIP();
	}
	

}