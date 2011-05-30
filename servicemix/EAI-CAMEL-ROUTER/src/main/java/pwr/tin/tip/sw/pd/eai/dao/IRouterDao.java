package pwr.tin.tip.sw.pd.eai.dao;

import java.util.List;

import pwr.tin.tip.sw.pd.eai.enums.UnitType;
import pwr.tin.tip.sw.pd.eai.model.Unit;

public interface IRouterDao {

	public List<Unit> getCentralUnitList();
	public List<Unit> getExecutiveUnitList();
	
	public List<Unit> marked(Integer id, UnitType unit);
	public void markUnit(Integer id, UnitType unit);
	public void clearMarkers(UnitType unit);
}
