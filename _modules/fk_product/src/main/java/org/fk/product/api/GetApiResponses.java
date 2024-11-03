package org.fk.product.api;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@APIResponses(value = {
    @APIResponse(responseCode = "200", description = "Request Successful"),
    @APIResponse(responseCode = "500", description = "Server unavailable")
})
public @interface GetApiResponses {

}
