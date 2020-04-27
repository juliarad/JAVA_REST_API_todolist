package com.company;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Path("/item")
public class ItemRest {
    @Inject
    private Logger logger;

    @Inject
    private ItemRegistrator registrator;

    @Inject
    private ItemRepository repository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAll(){
        logger.info("Get all items on the list");
        return repository.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getById(long id){
        logger.info("Get item by id "+id);
        return repository.getById(id);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Item item){
        logger.info("Create item "+item);
        registrator.create(item);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Item item){
        logger.info("Update item "+item);
        registrator.update(item);
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(Item item){
        logger.info("Delete item "+item);
        registrator.delete(item);
    }


}
