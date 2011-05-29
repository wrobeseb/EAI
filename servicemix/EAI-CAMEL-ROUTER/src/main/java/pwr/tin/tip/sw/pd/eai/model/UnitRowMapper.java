package pwr.tin.tip.sw.pd.eai.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pwr.tin.tip.sw.pd.eai.enums.UnitType;

public class UnitRowMapper implements RowMapper<Unit> {

	@Override
	public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Unit unit = new Unit();
		unit.setIdUnit(rs.getInt("id_unit"));
		unit.setType(rs.getInt("type") == 0 ? UnitType.CU : UnitType.EU);
		unit.setOverloadFlg(rs.getBoolean("overload_flg"));
		unit.setAddressIp(rs.getString("address_ip"));
		unit.setLastUpdateDt(rs.getDate("last_update_dt"));
		unit.setMaxProcessNo(rs.getInt("max_process_no"));
		unit.setServiceName(rs.getString("service_name"));
		unit.setInQueueName(rs.getString("in_queue_name"));
		unit.setOutQueueName(rs.getString("out_queue_name"));
		return unit;
	}

}
