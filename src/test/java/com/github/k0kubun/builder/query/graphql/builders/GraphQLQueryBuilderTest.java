package com.github.k0kubun.builder.query.graphql.builders;

import com.github.k0kubun.builder.query.graphql.GraphQL;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphQLQueryBuilderTest
{
    @Test public void buildEmptyQuery()
    {
        String query = GraphQL.createQueryBuilder()
            .build();
        assertEquals("", query);
    }

    @Test public void buildFields()
    {
        String query = GraphQL.createQueryBuilder()
            .field("id")
            .field("name")
            .build();
        assertEquals("id\nname\n", query);
    }
}
