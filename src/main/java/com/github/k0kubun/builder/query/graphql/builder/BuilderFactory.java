package com.github.k0kubun.builder.query.graphql.builder;

public class BuilderFactory {

    public ObjectBuilder buildObjectBuilder() {
        return new ObjectBuilderImpl();
    }

    public QueryBuilder buildQueryBuilder() {
        return new QueryBuilderImpl();
    }
}
