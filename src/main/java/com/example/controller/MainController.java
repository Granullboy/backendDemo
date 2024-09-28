package com.example.controller;

import com.example.service.GroupService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/main")
public class MainController {

    private GroupService groupService;
    public MainController() { this.groupService = new GroupService(); }

    @GET
    public void shutdown(){

    }
}
