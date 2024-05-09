package org.fk.core.query.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import org.fk.core.exception.MappingException;

import java.util.ArrayList;
import java.util.List;

/**
 * FkQuery
 * <p>
 * Deserialized form of query-parameters that can be used in GET-Requests for remote-pagination.
 * </p>
 */
@Schema(name = "FkQuery", description = "Query parameters for pagination, sorting, and filtering")
public class FkQuery {

    @QueryParam("page")
    @Schema(description = "Page number (starting from 0)", defaultValue = "0")
    private int page;

    @QueryParam("pageSize")
    @Schema(description = "Number of items per page", defaultValue = "10")
    private int pageSize;

    @QueryParam("sort")
    @Schema(description = "Sort order (e.g. name:asc)", defaultValue = "productId:asc")
    private String sort;

    @QueryParam("filter")
    @Schema(description = "Filter criteria (e.g. name:John,age:gt:30)", defaultValue = "productId:=:1,price:>=:10")
    private String filter;
    private FkSorter sorter;
    private List<FkFilter> filters = new ArrayList<>();

    @Context
    protected UriInfo uriInfo;

    public FkQuery() {
    }
    public FkQuery(UriInfo uriInfo) {
        // Only used for Unit-Test, as uriInfo is injected by quarkus in runtime via @Context.
        this.uriInfo = uriInfo;
    }

    @PostConstruct
    void init() {
        if (uriInfo == null) {
            throw new MappingException("init called without uriInfo available!");
        }
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        this.page = Integer.parseInt(queryParams.getFirst("page"));
        this.pageSize = Integer.parseInt(queryParams.getFirst("pageSize"));

        // Parse sort parameter
        String sorterString = queryParams.getFirst("sort");
        this.sorter = FkSorter.parseSorterString(sorterString);

        // Parse filter parameters
        String filterStrings = queryParams.getFirst("filter");
        this.filters = FkFilter.parseFilterString(filterStrings);

    }

    public FkQuery setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPage() {
        return page;
    }

    public FkQuery setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public FkQuery setFilters(List<FkFilter> filters) {
        this.filters = filters;
        return this;
    }

    public List<FkFilter> getFilters() {
        return filters;
    }

    public FkQuery setSorter(FkSorter sorter) {
        this.sorter = sorter;
        return this;
    }

    public FkSorter getSorter() {
        return sorter;
    }
}