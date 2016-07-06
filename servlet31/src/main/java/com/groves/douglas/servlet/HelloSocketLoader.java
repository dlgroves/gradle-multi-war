package com.groves.douglas.servlet;

import com.groves.douglas.socket.HelloSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

/**
 * Created by Douglas Groves on 06/07/2016.
 */
public class HelloSocketLoader implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloSocketLoader.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute("javax.websocket.server.ServerContainer");
        try {
            container.addEndpoint(HelloSocket.class);
        }catch(DeploymentException de){
            throw new RuntimeException(de);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
