package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.google.common.collect.ImmutableMap;
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
        assertEquals(
                "id\n" +
                "name\n",
                query);
    }

    @Test public void buildEmptyObject()
    {
        String query = GraphQL.createQueryBuilder()
            .object("query", GraphQL.createObjectBuilder()
                .build()
            ).build();
        assertEquals(
                "query {\n" +
                "}\n",
                query);
    }

    @Test public void buildObject()
    {
        String query = GraphQL.createQueryBuilder()
            .object("query", GraphQL.createObjectBuilder()
                .field("hello")
                .field("world")
                .build()
            ).build();
        assertEquals(
                "query {\n" +
                "  hello\n" +
                "  world\n" +
                "}\n",
                query);
    }

    @Test public void buildNestedObject()
    {
        String query = GraphQL.createQueryBuilder()
            .object("query", GraphQL.createObjectBuilder()
                .field("hello")
                .object("user", GraphQL.createObjectBuilder()
                    .field("world")
                    .field("the")
                    .object("nested", GraphQL.createObjectBuilder()
                        .field("things")
                        .build()
                    ).build()
                ).build()
            ).build();
        assertEquals(
                "query {\n" +
                "  hello\n" +
                "  user {\n" +
                "    world\n" +
                "    the\n" +
                "    nested {\n" +
                "      things\n" +
                "    }\n" +
                "  }\n" +
                "}\n",
                query);
    }

    @Test public void buildObjectWithParams()
    {
        String query = GraphQL.createQueryBuilder()
            .object("user", ImmutableMap.of("login", "k0kubun"), GraphQL.createObjectBuilder()
                .object("repository", ImmutableMap.of("name", "hamlit", "foo", 100), GraphQL.createObjectBuilder()
                    .field("id")
                    .build()
                ).build()
            ).build();
        assertEquals(
                "user(login:\"k0kubun\") {\n" +
                "  repository(name:\"hamlit\" foo:100) {\n" +
                "    id\n" +
                "  }\n" +
                "}\n",
                query);
    }
}