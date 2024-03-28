package org.fk.product.manager;

import io.hypersistence.tsid.TSID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.manager.AbstractManager;
import org.fk.core.tsid.TsidUtils;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.query.Filter;
import org.fk.core.util.query.FilterOperator;
import org.fk.core.util.query.QueryParameters;
import org.fk.product.dao.PostDAO;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dto.PostDTO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.repository.ProductRepository;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.exception.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.jooq.impl.DSL.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class PostManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(PostManager.class);

    @Inject
    TSID.Factory tsidFactory;

    @Transactional(rollbackOn = Exception.class)
    public PostDTO create(DSLContext dsl) throws ValidationException {
        PostDTO post = new PostDTO();

        TSID tsid = tsidFactory.generate();

        String myTsidStr = tsid.toString();
        TSID.isValid(myTsidStr);
        TSID revert = TSID.from(myTsidStr);

        post.setId(tsid.toLong());

        PostDAO postDAO = new PostDAO(dsl);
        return postDAO.insertAndReturnDTO(post);
    }
}
