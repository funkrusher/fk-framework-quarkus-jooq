package org.fk.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;


public interface DTO extends Serializable {
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper();
}