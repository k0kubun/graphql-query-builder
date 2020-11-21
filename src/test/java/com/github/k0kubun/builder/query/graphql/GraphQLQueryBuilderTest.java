package com.github.k0kubun.builder.query.graphql;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GraphQLQueryBuilderTest
{
    @Test
    public void testCreateQueryBuilder()
    {
        assertNotNull(GraphQLQueryBuilder.createQueryBuilder());
    }

    @Test
    public void testCreateObjectBuilder()
    {
        assertNotNull(GraphQLQueryBuilder.createObjectBuilder());
    }
}
