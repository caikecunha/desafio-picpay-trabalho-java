package dev.trabalhopicpay.controllers;

import dev.trabalhopicpay.entitys.user.User;
import dev.trabalhopicpay.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    // GET para retornar usuários
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userService.listUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser (User newUser) {
        User createUser = userService.saveUser(newUser);
        return Response.status(Response.Status.CREATED).entity(createUser).build();
    }
}
