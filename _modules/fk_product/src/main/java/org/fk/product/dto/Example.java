package org.fk.product.dto;

import dev.mccue.magicbean.MagicBean;

import java.util.List;

@MagicBean
public final class Example extends ExampleBeanOps {
    int x;
    String name;
    List<String> strs;
    String test;
}
