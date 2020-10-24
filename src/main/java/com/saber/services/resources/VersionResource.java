package com.saber.services.resources;

import com.saber.services.dto.VersionResourceV1;
import com.saber.services.dto.VersionResourceV2;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(value = "/version")
public class VersionResource {
    @Context
    private ResourceContext context;

    @Path(value = "/v1")
    @GET
    public Response v1() {
        VersionResourceV1 v1 = new VersionResourceV1();
        return v1.getV1();
    }

    @Path(value = "/v2")
    @GET
    public Response v2() {
        return context.getResource(VersionResourceV2.class).getV2();
    }
}
