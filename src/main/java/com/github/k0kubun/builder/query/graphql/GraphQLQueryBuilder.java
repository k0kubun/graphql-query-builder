package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builder.GraphQLBuilderFactory;
import com.github.k0kubun.builder.query.graphql.builder.ObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.QueryBuilder;

public class GraphQLQueryBuilder
{
    private GraphQLQueryBuilder()
    {
    }

    public static QueryBuilder query()
    {
        return new GraphQLBuilderFactory().buildQueryBuilder();
    }

    public static ObjectBuilder object()
    {
        return new GraphQLBuilderFactory().buildObjectBuilder();
    }
}
