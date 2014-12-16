package luna.tmm.rosettastone.application.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class RestRequestFilter implements ContainerRequestFilter {
 
    @Override
    public void filter(ContainerRequestContext requestContext)
                    throws IOException {
    	System.out.println("Filter...");
    	/* 
        final SecurityContext securityContext =
                    requestContext.getSecurityContext();
        if (securityContext == null ||
                    !securityContext.isUserInRole("privileged")) {
 
                requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build());
        }
        */
    }
}