package com.github.k0kubun.builder.query.graphql.example;

import com.github.k0kubun.builder.query.graphql.model.GraphQLParam;

public class ParamExample implements GraphQLParam {
    private String userId;
    private Integer from;
    private FilterExample filter;

    public void setFrom(Integer from) {
        this.from = from;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFilter(FilterExample filter) {
        this.filter = filter;
    }

}
