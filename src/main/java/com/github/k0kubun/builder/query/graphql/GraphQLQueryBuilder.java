package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builder.ObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.QueryBuilder;

public class GraphQLQueryBuilder
{
    public static QueryBuilder createQueryBuilder()
    {
        return new QueryBuilder();
    }

    public static ObjectBuilder createObjectBuilder()
    {
        return new ObjectBuilder();
    }
}
