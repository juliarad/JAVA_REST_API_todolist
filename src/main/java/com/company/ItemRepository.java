package com.company;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ItemRepository {
    @Inject
    private EntityManager entityManager;

    public List<Item> getAll(){
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteria=cb.createQuery(Item.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public Item getById(long id){
        return entityManager.find(Item.class, id);
    }

}
