package api_rest.rest;

import api_rest.model.User;
import api_rest.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ferlopez on 02/06/2017.
 */

@Path("users")
@RequestScoped
public class UserResource {

    @EJB
    UserService userService;

    @GET
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@PathParam("id") Long id) throws NotFoundException{
        return userService.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> query(){
        return userService.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user){
        userService.add(user);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{id: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(User user, @PathParam("id") Long id) throws NotFoundException{
        user.setId(id);
        userService.update(user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/{id: \\d+}")
    public Response remove(@PathParam("id") Long id) throws NotFoundException{
        userService.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
