package com.example.controller;
/*
import com.example.moduls.GroupMember;
import com.example.service.GroupMemberService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/groupmembers")
public class GroupMemberController {
    private GroupMemberService groupMemberService;
    public GroupMemberController() {this.groupMemberService = new GroupMemberService(); }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<GroupMember> getGroupMembers() { return groupMemberService.getGroupMembers(); }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupMemberById(@PathParam("id") int id) { return Response.ok(groupMemberService.getGroupMemberById(id)).build(); }

    @GET
    @Path("/group{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupMemberByGroupId(@PathParam("id") int id) { return Response.ok(groupMemberService.getGroupMembersByGroupId(id)).build(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGroupMember(GroupMember groupMember) {
        groupMemberService.addGroupMember(groupMember);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGroupMember(@PathParam("id") int id, GroupMember groupMember) {
        groupMemberService.updateGrgoupMember(groupMember);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGroupMember(@PathParam("id") int id) {
        groupMemberService.deleteGroupMember(id);
        return Response.status(Response.Status.OK).build();
    }

}*/
