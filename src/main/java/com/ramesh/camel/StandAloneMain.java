package com.ramesh.camel;

import org.slf4j.*;
import org.apache.camel.*;
import org.apache.camel.impl.*;
import org.apache.camel.builder.*;

public class StandAloneMain {
	static Logger LOG = LoggerFactory.getLogger(StandAloneMain.class);

	public static void main(String[] args) throws Exception {
		new StandAloneMain().run();
	}

	void run() throws Exception {
		final CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(createRouteBuilder());
		camelContext.setTracing(true);
		
		camelContext.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					camelContext.stop();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		waitForStop();
	}

	RouteBuilder createRouteBuilder() {
		return new CamelRouteBuilder();
	}

	void waitForStop() {
		while (true) {
			try {
				//Thread.sleep(Long.MAX_VALUE);
			} catch (Exception e) {
				break;
			}
		}
	}
}