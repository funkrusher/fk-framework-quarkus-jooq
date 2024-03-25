package org.fk.core.mapper;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.codegen.dto.AbstractDTO;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.request.RequestContext;
import org.jooq.DSLContext;
import org.jooq.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A common base-class for Mappers
 * <p>
 * This type is implemented by Mapper classes.
 * A mapper will be responsible to map database records
 * (possibly 'Kartesisches Produkt' for N->M relationships) to the related DTOs and vice versa.
 * </p>
 */
public abstract class AbstractMapper {

    private final RequestContext request;

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractMapper(RequestContext request) {
        this.request = request;
    }

    // -------------------------------------------------------------------------
    // Mapper API
    // -------------------------------------------------------------------------

    /**
     * Expose the request this <code>Mapper</code> is operating.
     *
     * @return the <code>Mapper</code>'s underlying <code>request</code>
     */
    public RequestContext request() {
        return this.request;
    }
}