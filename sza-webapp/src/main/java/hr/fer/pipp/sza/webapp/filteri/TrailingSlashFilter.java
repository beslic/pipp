package hr.fer.pipp.sza.webapp.filteri;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class TrailingSlashFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String url = requestContext.getUriInfo().getAbsolutePath().toString();
		if (url != null && !url.endsWith("/")) {
			requestContext.abortWith(Response.seeOther(URI.create(url + "/")).status(Status.MOVED_PERMANENTLY).build());
		}
	}

}
