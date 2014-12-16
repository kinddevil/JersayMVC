package luna.tmm.rosettastone.application.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class RestResponseFilter implements ContainerResponseFilter{
	@Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
        throws IOException {
            //responseContext.getHeaders().add("X-Powered-By", "Jersey :-)");
    }
}
