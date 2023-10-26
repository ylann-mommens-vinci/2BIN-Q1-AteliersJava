package be.vinci.api.filters;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DebugMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {
        t.printStackTrace();
        return Response.serverError().entity(t.getMessage()).build();
    }

}