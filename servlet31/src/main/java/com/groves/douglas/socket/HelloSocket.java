package com.groves.douglas.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Douglas Groves on 05/07/2016.
 */
@ServerEndpoint(value = "/foobar")
public class HelloSocket {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloSocket.class);

    @OnOpen
    public void init(Session session) {
        LOGGER.info("Incoming request from {} = {}",
                session.getRequestURI().getHost(), session.getId());
    }

    @OnMessage
    public String service(String message, Session session) {
        LOGGER.info("Received message [{}] from {}", message, session.getId());
        return message;
    }

    @OnClose
    public void destroy(Session session, CloseReason reason) {
        LOGGER.info("Session {} closed by {} due to {}", session.getId(),
                session.getRequestURI().getHost(), reason);
    }

}
