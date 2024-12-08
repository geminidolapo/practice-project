package com.practice.project.service.sample;

import org.springframework.stereotype.Component;

@Component
public class Scope {

//    @Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public void scopeExample(){
//        Prototype Scope:
//        A new instance/object will be created whenever the bean is requested from the spring container.

//        Request Scope:
//        @RequestScope
//        A new instance will be created every HTTP request from the application.

//        Session Scope:
//        @SessionScope
//        A new instance will be created every HTTP session from the application.


//        Application Scope:
//        @ApplicationScope
//        A new instance will be created once for the entire application.
//            It’s similar to singleton scope but the difference is singleton scope per ServletContext, not per Spring ApplicationContext.

//        WebSocket Scope:
//        This scope is used when two-way communication between a client and server enabled using the websocket protocol.
//        It is mainly useful when applications are used by multiple users with simultaneous actions.
    }
}
