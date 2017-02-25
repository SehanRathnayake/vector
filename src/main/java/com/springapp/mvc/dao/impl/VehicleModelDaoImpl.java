package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.VehicleModelDao;
import com.springapp.mvc.model.Job;
import com.springapp.mvc.model.Model;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 17/02/25.
 */
@Repository
public class VehicleModelDaoImpl extends BaseJpaDaoImpl<Model> implements VehicleModelDao {

    public Model addModel(Model model) {

        return saveEntity(model);
    }

    public Model getModel(long id) {
        return getEntity(Model.class, (int)id);
    }

    public List<Model> getModelList() {
        String queryString = "SELECT m FROM Model m";
        TypedQuery<Model> query = this.entityManager.createQuery(queryString, Model.class);
        return query.getResultList();
    }


}


