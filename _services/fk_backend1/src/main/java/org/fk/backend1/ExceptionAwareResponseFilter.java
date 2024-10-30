package org.fk.backend1;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionAwareResponseFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (Boolean.TRUE.equals(requestContext.getProperty("abortRequest"))) {
            requestContext.abortWith(Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity("Aborted by filter")
                .build());
        }
    }
}