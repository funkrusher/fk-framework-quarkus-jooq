package org.fk.core.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.AbstractDTO;

public abstract class AbstractTaskDTO extends AbstractDTO {

    @JsonIgnore
    @XmlTransient
    protected abstract Class getExecutorClass();
}
