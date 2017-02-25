package com.springapp.mvc.dao;

import com.springapp.mvc.model.Model;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 17/02/25.
 */
public interface VehicleModelDao {
    public Model addModel(Model model);

    public Model getModel(long id);

    public List<Model> getModelList();
}