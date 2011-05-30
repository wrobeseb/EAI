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
	public Integer getLessLoadedCentralUnitId() {
		List<Unit> centralUnitList = routerDao.getCentralUnitList();
		if (centralUnitList.size() != 0) {
			for (Unit unit : centralUnitList) {
				if (!unit.getOverloadFlg()) {
					routerDao.markUnit(unit.getIdUnit(), UnitType.CU.ordinal());
					return unit.getIdUnit();
				}
			}
			return centralUnitList.get(0).getIdUnit();
		}
		else {
			return 0;
		}
	}

	@Override
	public Integer getLessLoadedExecutiveUnitId() {
		List<Unit> executiveUnitList = routerDao.getExecutiveUnitList();
		if (executiveUnitList.size() != 0) {
			for (Unit unit : executiveUnitList) {
				if (!unit.getOverloadFlg()) {
					return unit.getIdUnit();
				}
			}
			return executiveUnitList.get(0).getIdUnit();
		}
		else {
			return 0;
		}
	}
}
