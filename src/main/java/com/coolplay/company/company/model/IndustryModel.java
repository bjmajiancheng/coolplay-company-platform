/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.coolplay.company.company.model;

import com.coolplay.company.common.handler.Sortable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 * @author  shawn
 * @version 1.0
 * @since 1.0
 */
@Table(name = "d_industry")
public class IndustryModel extends Sortable {
	private static final long serialVersionUID = 1L;

	//columns START
	@Column(name = "id")
	private Integer id;//"id"

	@Column(name = "industry_name")
	private String industryName;//"行业名称"

	@Column(name = "industry_desc")
	private String industryDesc;//"行业备注"

	@Column(name = "c_time")
	private Date ctime;//"创建时间"

	@Column(name = "u_time")
	private Date utime;//"更新时间"

	//columns END
		
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
		
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryName() {
		return this.industryName;
	}
		
	public void setIndustryDesc(String industryDesc) {
		this.industryDesc = industryDesc;
	}

	public String getIndustryDesc() {
		return this.industryDesc;
	}
		
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getCtime() {
		return this.ctime;
	}
		
	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Date getUtime() {
		return this.utime;
	}

}

