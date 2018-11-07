package com.api.data.user.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.api.data.pojo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户：接口使用证书鉴权、页面使用密码登陆
 */
@Entity
@Table(name = "tb_user")
@GenericGenerator(name = "sequenceGenerator", strategy = "uuid")
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_NAME = "name";
	
	@NotBlank(message = "用户名不能为空")
	private String name;
	@NotBlank(message = "用户密码不能为空")
	private String password;
	@JsonIgnore
	private String publicKey;
	@JsonIgnore
	private String privateKey;

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 2048)
	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Column(length = 2048)
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
