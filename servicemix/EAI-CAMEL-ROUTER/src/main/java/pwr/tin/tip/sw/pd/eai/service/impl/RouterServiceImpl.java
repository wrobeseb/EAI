package pwr.tin.tip.sw.pd.eai.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.eai.dao.IRouterDao;
import pwr.tin.tip.sw.pd.eai.service.IRouterService;

@Component(value="routerService")
public class RouterServiceImpl implements IRouterService {

	@Autowired(required=true)
	private IRouterDao routerDao;

	@Override
	public void getLoadInfo() {
		List<Map<String, Object>> lista = routerDao.getLoadInfo();
		lista.size();
	}
}
