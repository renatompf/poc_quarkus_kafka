package io.renatofreire.resource;

import io.renatofreire.dto.Message;
import io.renatofreire.dto.User;
import io.renatofreire.producer.KProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/producer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProducerResource {

    @Inject
    KProducer kafkaProducer;

    @POST
    public String sendMessage(Message message) {
        kafkaProducer.sendMessage(message.message());
        return "Message sent";
    }

    @POST
    @Path("/user")
    public String sendUser(User user) {
        kafkaProducer.sendUser(user);
        return "Message sent";
    }

}
