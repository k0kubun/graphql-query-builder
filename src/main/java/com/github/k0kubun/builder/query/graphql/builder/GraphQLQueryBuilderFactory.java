package com.github.k0kubun.builder.query.graphql.builder;

public class GraphQLQueryBuilderFactory {

    public ObjectBuilder buildObjectBuilder() {
        return new ObjectBuilderImpl();
    }

    public QueryBuilder buildQueryBuilder() {
        return new QueryBuilderImpl();
    }
}
