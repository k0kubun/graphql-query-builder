package com.github.k0kubun.builder.query.graphql.builders;

public class GraphQLQueryBuilder
{
    private final StringBuilder queryBuilder;

    public GraphQLQueryBuilder()
    {
        queryBuilder = new StringBuilder();
    }

    public String build()
    {
        return queryBuilder.toString();
    }

    public GraphQLQueryBuilder field(String name)
    {
        queryBuilder.append(name);
        queryBuilder.append("\n");
        return this;
    }
}
