package com.company;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Stateless
public class ItemRegistrator {

    @Inject
    private EntityManager entityManager;

    public void create (Item item){
        entityManager.merge(item);
    }

    public void update (Item item){
        entityManager.merge(item);
    }

    public void delete (Item item){
        entityManager.remove(item);
    }
}
