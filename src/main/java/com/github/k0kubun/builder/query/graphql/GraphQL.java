package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builders.GraphQLObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builders.GraphQLQueryBuilder;

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
