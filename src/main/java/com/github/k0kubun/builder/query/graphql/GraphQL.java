package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builder.GraphQLObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.GraphQLQueryBuilder;

public class GraphQL
{
    public static GraphQLQueryBuilder createQueryBuilder()
    {
        return new GraphQLQueryBuilder();
    }

    public static GraphQLObjectBuilder createObjectBuilder()
    {
        return new GraphQLObjectBuilder();
    }
}
