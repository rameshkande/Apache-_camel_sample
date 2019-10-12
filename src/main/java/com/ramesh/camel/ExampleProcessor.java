package com.ramesh.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExampleProcessor implements Processor {


	public void process(Exchange exchange) throws Exception {
		String inBody = exchange.getIn().getBody(String.class);
		System.out.println(inBody);
		exchange.getIn().setBody(inBody);
	}

}
