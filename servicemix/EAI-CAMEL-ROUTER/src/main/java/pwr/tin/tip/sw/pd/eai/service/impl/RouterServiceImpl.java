package pwr.tin.tip.sw.pd.eai.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.eai.dao.IRouterDao;
import pwr.tin.tip.sw.pd.eai.enums.UnitType;
import pwr.tin.tip.sw.pd.eai.model.Unit;
import pwr.tin.tip.sw.pd.eai.service.IRouterService;

@Component(value="routerService")
public class RouterServiceImpl implements IRouterService {

	@Autowired(required=true)
	private IRouterDao routerDao;

	@Override
	public Integer getLessLoadedCentralUnitId() throws Exception {
		return getLessLoadedUnit(routerDao.getCentralUnitList(), UnitType.CU).getIdUnit();
	}

	@Override
	public Integer getLessLoadedExecutiveUnitId() throws Exception {
		return getLessLoadedUnit(routerDao.getExecutiveUnitList(), UnitType.EU).getIdUnit();
	}
	
	private Unit getLessLoadedUnit(List<Unit> unitList, UnitType unitType) throws Exception {
		Unit selectedUnit = null;
		if (unitList.size() != 0) {
			if (!isAllMarked(unitList, unitType)) {
				for (Unit unit : unitList) {
					if (!unit.getOverloadFlg() && !unit.isMark()) {
						selectedUnit = unit; break;
					}
				}
			}
			if (selectedUnit == null) {
				selectedUnit = unitList.get(0);
			}
		}
		else {
			throw new Exception("Brak zarejstrowanych jednostek w bazie danych");
		}
		routerDao.markUnit(selectedUnit.getIdUnit(), unitType);
		return selectedUnit;
	}
	
	private Boolean isAllMarked(List<Unit> unitList, UnitType unitType) {
		for (Unit unit : unitList) {
			if (!unit.isMark()) {
				return false;
			}
		}
		routerDao.clearMarkers(unitType);
		return true;
	}
}
