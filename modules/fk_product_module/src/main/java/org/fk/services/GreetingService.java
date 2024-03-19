package org.fk.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import org.fk.daos.DAOFactory;
import org.fk.daos.ProductLangRecordDAO;
import org.fk.daos.ProductRecordDAO;
import org.fk.daos.ProductViewDAO;
import org.fk.dtos.ProductDTO;
import org.fk.dtos.ProductLangDTO;
import org.fk.generated.testshop.tables.Product;
import org.fk.generated.testshop.tables.records.ProductLangRecord;
import org.fk.generated.testshop.tables.records.ProductRecord;
import org.fk.jooq.JooqContext;
import org.fk.jooq.JooqContextFactory;
import org.fk.util.exception.ValidationException;
import org.fk.util.query.QueryParameters;
import org.fk.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.exception.DataAccessException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * ProductService
 */
@ApplicationScoped
public class GreetingService {

    private static final Logger LOGGER = Logger.getLogger(GreetingService.class);

    @Inject
    JooqContextFactory jooqContextFactory;

    @Inject
    DAOFactory daoFactory;

    @Inject
    Validator validator;


    public String test() {
        return "Hello my world";
    }
}
