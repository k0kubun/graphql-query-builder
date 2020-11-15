package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builder.BuilderFactory;
import com.github.k0kubun.builder.query.graphql.builder.ObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.QueryBuilder;

public class GraphQLQueryBuilder
{
    private GraphQLQueryBuilder() {
    }

    public static QueryBuilder createQueryBuilder()
    {
        return new BuilderFactory().buildQueryBuilder();
    }

    public static ObjectBuilder createObjectBuilder()
    {
        return new BuilderFactory().buildObjectBuilder();
    }
}
