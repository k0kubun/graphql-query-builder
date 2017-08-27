package com.github.k0kubun.builder.query.graphql;

import com.github.k0kubun.builder.query.graphql.builders.GraphQLObjectBuilder;

public class GraphQL
{
    public static GraphQLObjectBuilder createObjectBuilder()
    {
        return new GraphQLObjectBuilder();
    }
}
