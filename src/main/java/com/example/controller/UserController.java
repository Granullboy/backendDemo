package com.example.controller;

import com.example.moduls.Group;
import com.example.moduls.GroupMessage;
import com.example.moduls.User;
import com.example.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/users")
public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(@QueryParam("username") String username, @QueryParam("password") String password) {
        User user = userService.Login(username, password);
        if (user == null) { return Response.status(Response.Status.UNAUTHORIZED).build(); }
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int userId) {
        User user = userService.getUserById(userId);
        if (user == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Path("/name:{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupMessageById(@PathParam("username") String username) {
        List<User> users = userService.getUsersByName(username);
        if (users == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/{id}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsByUserId(@PathParam("id") int userId) {
        Set<Group> groups = userService.getGroupsByUser(userId);
        return Response.status(Response.Status.OK).entity(groups).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        User checkUser = userService.Register(user);
        if (checkUser == null) { return Response.status(Response.Status.UNAUTHORIZED).build(); }
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int userId, User user) {
        user.setUser_id(userId);
        User checkUser = userService.updateUser(user);
        if (checkUser == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int userId) {
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        return Response.status(Response.Status.OK).entity(user).build();
    }
}
