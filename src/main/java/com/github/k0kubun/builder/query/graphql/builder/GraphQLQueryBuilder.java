package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.model.GraphQLObject;

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
        appendln(name);
        return this;
    }

    public GraphQLQueryBuilder object(String name, GraphQLObject object)
    {
        append(name);
        appendln(" {");
        appendln("}");
        return this;
    }

    private void append(String str)
    {
        queryBuilder.append(str);
    }

    private void appendln(String line)
    {
        queryBuilder.append(line);
        queryBuilder.append("\n");
    }
}
