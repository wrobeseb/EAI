package pwr.tin.tip.sw.pd.eai.dao.impl;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pwr.tin.tip.sw.pd.eai.dao.IRouterDao;
import pwr.tin.tip.sw.pd.eai.enums.UnitType;
import pwr.tin.tip.sw.pd.eai.model.Unit;
import pwr.tin.tip.sw.pd.eai.model.UnitRowMapper;

@Repository(value="routerDao")
public class RouterDaoImpl implements IRouterDao {

	private JdbcTemplate template;
	
	@Autowired(required=true)
	public void setDataSource(BasicDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Unit> getCentralUnitList() {
		return template.query("SELECT * FROM unit WHERE type = 0", new UnitRowMapper());
	}

	@Override
	public List<Unit> getExecutiveUnitList() {
		return template.query("SELECT * FROM unit WHERE type = 1", new UnitRowMapper());
	}

	@Override
	public void markUnit(Integer id, UnitType unit) {
		template.update("UPDATE unit SET marked = true WHERE id_unit = ? AND type = ?", id, unit.ordinal());
	}

	@Override
	public void clearMarkers(UnitType unit) {
		template.update("UPDATE unit SET marked = false WHERE type = ?", unit.ordinal());
	}

	@Override
	public List<Unit> marked(Integer id, UnitType unit) {
		return template.query("SELECT * FROM unit WHERE id_unit = ? AND type = ?", new UnitRowMapper(), id, unit.ordinal());
	}
}
