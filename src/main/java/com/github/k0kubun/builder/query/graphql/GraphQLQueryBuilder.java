package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builder.GraphQLQueryBuilderFactory;
import com.github.k0kubun.builder.query.graphql.builder.ObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.QueryBuilder;

public class GraphQLQueryBuilder
{
    private GraphQLQueryBuilder()
    {
    }

    public static QueryBuilder createQueryBuilder()
    {
        return new GraphQLQueryBuilderFactory().buildQueryBuilder();
    }

    public static ObjectBuilder createObjectBuilder()
    {
        return new GraphQLQueryBuilderFactory().buildObjectBuilder();
    }
}
