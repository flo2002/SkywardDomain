package fhv.ws22.se.skyward.domain.dtos;

import fhv.ws22.se.skyward.domain.model.AbstractModel;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractDto implements Serializable {
    private UUID id;

    public AbstractDto() {
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
