package com.github.k0kubun.builder.query.graphql;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphQLTest
{
    @Test
    public void testCreateQueryBuilder()
    {
        assertNotNull(GraphQL.createQueryBuilder());
    }

    @Test
    public void testCreateObjectBuilder()
    {
        assertNotNull(GraphQL.createObjectBuilder());
    }
}
