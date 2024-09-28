package com.example.controller;

import com.example.moduls.GroupMessage;
import com.example.service.GroupMessageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/messages")
public class GroupMessageController {
    private GroupMessageService groupMessageService;
    public GroupMessageController() { this.groupMessageService = new GroupMessageService(); }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupMessages() {
        List<GroupMessage> groupMessages = groupMessageService.getAllGroupMessages();
        if (groupMessages.isEmpty()) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(groupMessages).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupMessageById(@PathParam("id") int id) {
        GroupMessage groupMessage = groupMessageService.getGroupMessageById(id);
        if (groupMessage == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(groupMessage).build();
    }

    @GET
    @Path("/group:{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageByGroupId(@PathParam("id") int id) {
        List<GroupMessage> groupMessages = groupMessageService.getMessagesByGroupId(id);
        if (groupMessages == null) { return Response.status(Response.Status.NOT_FOUND).build(); }
        if (groupMessages.isEmpty()) { return Response.status(Response.Status.NOT_FOUND).build(); }
        return Response.status(Response.Status.OK).entity(groupMessages).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGroupMessage(GroupMessage groupMessage) {
        groupMessageService.addGroup(groupMessage);
        return Response.status(Response.Status.OK).entity(groupMessage).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGroupMessage(@PathParam("id") int id, GroupMessage groupMessage) {
        groupMessage.setMessage_id(id);
        groupMessageService.updateGroup(groupMessage);
        return Response.status(Response.Status.CREATED).entity(groupMessage).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGroupMessage(@PathParam("id") int id) {
        groupMessageService.deleteGroup(id);
        return Response.status(Response.Status.OK).build();
    }
}
