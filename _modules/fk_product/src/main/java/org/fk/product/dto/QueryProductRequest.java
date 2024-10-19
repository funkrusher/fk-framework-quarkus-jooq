package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;

// see: https://stackoverflow.com/questions/76765016/how-to-pass-get-query-parameters-in-quarkus-as-pojo-object
public class QueryProductRequest{
    @QueryParam("page") @NotNull Integer page;
    @QueryParam("pageSize") @NotNull Integer pageSize;
    @QueryParam("sort") String sort;
    @QueryParam("filter") String filter;
}