package com.baloise.waf.operator;

import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RouteRessourceWatcher implements Watcher<Route>{

    private final Logger log = LoggerFactory.getLogger(RouteRessourceWatcher.class);
}
