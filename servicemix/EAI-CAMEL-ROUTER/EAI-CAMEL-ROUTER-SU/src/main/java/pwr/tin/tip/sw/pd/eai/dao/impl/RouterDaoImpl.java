package pwr.tin.tip.sw.pd.eai.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pwr.tin.tip.sw.pd.eai.dao.IRouterDao;

@Repository(value="routerDao")
public class RouterDaoImpl implements IRouterDao {

	private JdbcTemplate template;
	
	@Autowired(required=true)
	public void setDataSource(BasicDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Map<String, Object>> getLoadInfo() {
		return template.queryForList("select * from router_test");
	}
}
