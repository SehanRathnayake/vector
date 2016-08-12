package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.PositionDao;
import com.springapp.mvc.model.Position;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */

@Repository
public class PositionDaoImpl extends BaseJpaDaoImpl<Position> implements PositionDao{

    public Position createPosition(Position position){
       return saveEntity(position);
    }

    public Position getPositionById(Long id){
       return getEntity(Position.class,id);
    }



}
