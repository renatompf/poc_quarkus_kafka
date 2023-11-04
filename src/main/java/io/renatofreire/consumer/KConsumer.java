package io.renatofreire.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.renatofreire.dto.User;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KConsumer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Incoming("destination")
    public void sink(String message) {

        try{
            User user = objectMapper.readValue(message, User.class);
            logger.info("A user was received: >> " + user.toString() + " <<");
        } catch (JsonProcessingException e) {
            logger.info(">> " + message + " <<");
        }

    }

}
