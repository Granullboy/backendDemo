package com.example.controller;

import com.example.moduls.Group;
import com.example.service.GroupService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/groups")
public class GroupController {
    private GroupService groupService;
    public GroupController() { this.groupService = new GroupService(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups() {
        List<Group> groups = groupService.getAllGroups();
        if (groups.isEmpty()) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(groups).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupById(@PathParam("id") int id) {
        Group group = groupService.getGroupById(id);
        if (group == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(group).build();
    }

    @GET
    @Path("/owner:{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupByOwnerId(@PathParam("id") int id) {
        List<Group> groups = groupService.getGroupByOwnerId(id);
        if (groups.isEmpty()) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(groups).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGroup(Group group) {
        System.out.println(group.toString());
        groupService.addGroup(group);
        return Response.status(Response.Status.CREATED).entity(group).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGroup(@PathParam("id") int id, Group group) {
        groupService.updateGroup(group);
        return Response.status(Response.Status.OK).entity(group).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGroup(@PathParam("id") int id) {
        Group group = groupService.getGroupById(id);
        groupService.deleteGroup(group);
        return Response.status(Response.Status.OK).build();
    }
}
