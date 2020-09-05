package io.github.wesleyosantos91.exception.interceptor;

import io.github.wesleyosantos91.exception.core.ObjectNotFoundException;
import io.swagger.model.ResponseErro;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status;

@Provider
public class ObjectNotFoundExceptionInterceptor implements ExceptionMapper<ObjectNotFoundException> {

    @Override
    public Response toResponse(ObjectNotFoundException e) {

        ResponseErro error = new ResponseErro();
        error.setStatus(Status.NOT_FOUND.getStatusCode());
        error.setErro(Status.NOT_FOUND.getReasonPhrase());
        error.setMensagem(e.getMessage());

        return Response.status(Status.NOT_FOUND).entity(error).type("application/json").build();
    }
}
