package org.fk.core.query;

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
 * QueryParameters
 * <p>
 * Deserialized form of query-parameters that can be used in GET-Requests for remote-pagination.
 * </p>
 */
@Schema(name = "QueryParameters", description = "Query parameters for pagination, sorting, and filtering")
public class QueryParameters {

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
    private Sorter sorter;
    private List<Filter> filters = new ArrayList<>();

    @Context
    protected UriInfo uriInfo;

    public QueryParameters() {
    }
    public QueryParameters(UriInfo uriInfo) {
        // Only used for Unit-Test, as uriInfo is injected by quarkus in runtime via @Context.
        this.uriInfo = uriInfo;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
        this.sorter = Sorter.parseSorterString(sorterString);

        // Parse filter parameters
        String filterStrings = queryParams.getFirst("filter");
        this.filters = Filter.parseFilterString(filterStrings);

    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Sorter getSorter() {
        return sorter;
    }
}