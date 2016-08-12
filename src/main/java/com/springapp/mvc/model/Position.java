package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */

@Entity
@Table(name = "T_POSITION")
public class Position implements Serializable {

    private static final long serialVersionUID = 1960820966066482915L;

    @SequenceGenerator(name = "Position_Gen", sequenceName = "Position_Seq")
    @Id
    @GeneratedValue(generator = "Position_Gen")
    @Column(name = "POSITION_ID")
    private long positionId;

    @Column(name = "DESCRIPTION")
    private String description;

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }
}
