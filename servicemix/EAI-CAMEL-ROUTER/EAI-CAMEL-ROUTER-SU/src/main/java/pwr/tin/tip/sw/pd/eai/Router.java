package pwr.tin.tip.sw.pd.eai;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Exchange;
import org.apache.camel.RecipientList;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

public class Router {

	public final static String JBI_PREFIX = "jbi:endpoint:http://pwr.tin.tip.sw.pd.eai";
	
	@Consume(uri=JBI_PREFIX + "/kernelDSLRouter/EAI")
	@RecipientList
	public String route(@Body String body, Exchange exchange){
		String algorithmName = getString(body, "/algorithm/algorithmName");
		String algorithmEndpoint = getString(body, "/algorithm/algorithmEndpoint");
		if (algorithmEndpoint != null) {
			return JBI_PREFIX + "/algorithmsInService/EAI";
		}
		else {
			if (algorithmName != null) {
				return JBI_PREFIX + "/algorithmsInService/EAI";
			}
		}
		return null;
	}
	
	private String getString(String messageBody, String xpathExpression) {
		try {
			Node node = DocumentHelper.parseText(messageBody).selectSingleNode(xpathExpression);
			if (node != null) {
				return node.getStringValue();	
			}
			else {
				return null;
			}
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}