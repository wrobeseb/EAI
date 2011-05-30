package pwr.tin.tip.sw.pd.eai.model;

import java.util.Date;

import pwr.tin.tip.sw.pd.eai.enums.UnitType;

public class Unit {
	private Integer idUnit;
	private UnitType type;
	private Boolean overloadFlg;
	private String addressIp;
	private Date lastUpdateDt;
	private Integer maxProcessNo;
	private boolean mark;
	
	public Integer getIdUnit() {
		return idUnit;
	}
	public void setIdUnit(Integer idUnit) {
		this.idUnit = idUnit;
	}
	public UnitType getType() {
		return type;
	}
	public void setType(UnitType type) {
		this.type = type;
	}
	public Boolean getOverloadFlg() {
		return overloadFlg;
	}
	public void setOverloadFlg(Boolean overloadFlg) {
		this.overloadFlg = overloadFlg;
	}
	public String getAddressIp() {
		return addressIp;
	}
	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}
	public Date getLastUpdateDt() {
		return lastUpdateDt;
	}
	public void setLastUpdateDt(Date lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	public Integer getMaxProcessNo() {
		return maxProcessNo;
	}
	public void setMaxProcessNo(Integer maxProcessNo) {
		this.maxProcessNo = maxProcessNo;
	}
	public boolean isMark() {
		return mark;
	}
	public void setMark(boolean mark) {
		this.mark = mark;
	}
}
