package com.api.data.user.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.api.data.pojo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * entity - 用户<br>
 * 接口使用证书鉴权、页面使用密码登陆
 */
@Entity
@Table(name = "tb_user", indexes = {
	@Index(name = "index_user_name", columnList = "name", unique = true)
})
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_NAME = "name"; // 用户名
	public static final String PROPERTY_PASSWORD = "password"; // 密码
	public static final String PROPERTY_PUBLIC_KEY = "publicKey"; // 公钥
	public static final String PROPERTY_PRIVATE_KEY = "privateKey"; // 私钥
	
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
