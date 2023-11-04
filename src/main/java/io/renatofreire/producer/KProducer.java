package io.renatofreire.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.renatofreire.dto.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KProducer {

    @Inject
    @Channel("source")
    Emitter<String> emitter;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(KProducer.class);


    public Message<String> sendMessage(String message){
        Message<String> messageToTopic = Message.of(message);
        emitter.send(messageToTopic);
        return messageToTopic;
    }

    public Message<String> sendUser(User user){
        Message<String> messageToTopic = null;
        try {
            messageToTopic = Message.of(objectMapper.writeValueAsString(user));
            emitter.send(messageToTopic);
        } catch (JsonProcessingException e) {
            logger.warn("Error converting to JSON");
        }
        return messageToTopic;
    }

}
