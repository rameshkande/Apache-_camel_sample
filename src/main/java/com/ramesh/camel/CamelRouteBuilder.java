package com.ramesh.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelRouteBuilder extends RouteBuilder {

	public void configure() {
		from("timer:fire?period=5000").setBody(constant("Messgae  or any object")).process(new ExampleProcessor())
				.to("log:MyRoute").process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String body = exchange.getIn().getBody(String.class)+" passing to second porocessor";
						System.out.println("CamelRouteBuilder -1:- " + body);
						exchange.getIn().setBody(body);
					}
				}).process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String body = exchange.getIn().getBody(String.class);
						System.out.println("CamelRouteBuilder - 2:- " + body + " in second porocessor");
						body = body.substring("Prefixed ".length());
						
					}
				}).to("log:MyRoute");
	}

}
