package pwr.tin.tip.sw.pd.eai;

import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.RecipientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.eai.enums.UnitType;
import pwr.tin.tip.sw.pd.eai.service.IRouterService;

@Component(value="router")
public class Router {

	public final static String JBI_PREFIX = "jbi:endpoint:http://pwr.tin.tip.sw.pd.eai";
	
	@Autowired(required=true)
	private IRouterService routerService;
	
	@Consume(uri=JBI_PREFIX + "/WF-CU-DSLRouter/EAI")
	@RecipientList
	public String euRoute(Exchange exchange){
		exchange.getIn().setHeader("cuId", loadBalancer(UnitType.CU));
		return JBI_PREFIX + "/CU-WF-RequestService/EAI";
	}
	
	@Consume(uri=JBI_PREFIX + "/CU-EU-DSLRouter/EAI")
	@RecipientList
	public String cuRoute(Exchange exchange){
		exchange.getIn().setHeader("euId", loadBalancer(UnitType.EU));
		return JBI_PREFIX + "/EU-CU-RequestService/EAI";
	}
	
	private Integer loadBalancer(UnitType unit) {
		switch (unit) {
			case CU:
				return routerService.getLessLoadedCentralUnitId();
			case EU:
				return routerService.getLessLoadedExecutiveUnitId();
		}
		return null;
	}
}
