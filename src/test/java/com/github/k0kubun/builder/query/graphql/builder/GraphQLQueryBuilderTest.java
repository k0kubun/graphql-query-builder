package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphQLQueryBuilderTest
{
    @Test
    public void buildEmptyQuery()
    {
        String query = GraphQL.createQueryBuilder()
                .build();
        assertEquals("", query);
    }

    @Test
    public void buildFields()
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

    @Test
    public void buildEmptyObject()
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

    @Test
    public void buildObject()
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

    @Test
    public void buildNestedObject()
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

    @Test
    public void buildObjectWithParams()
    {
        String query = GraphQL.createQueryBuilder()
                .object("user", ImmutableMap.of("login", "k0kubun"), GraphQL.createObjectBuilder()
                        .object("repository", ImmutableMap.of("name", "\"name\"", "foo", 100), GraphQL.createObjectBuilder()
                                .field("id")
                                .build()
                        ).build()
                ).build();
        assertEquals(
                "user(login:\"k0kubun\") {\n" +
                        "  repository(name:\"\\\"name\\\"\" foo:100) {\n" +
                        "    id\n" +
                        "  }\n" +
                        "}\n",
                query);
    }

    @Test
    public void buildObjects()
    {
        String query = GraphQL.createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), GraphQL.createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, GraphQL.createObjectBuilder()
                                .field("totalCount")
                                .object("edges", GraphQL.createObjectBuilder()
                                        .field("cursor")
                                        .object("node", GraphQL.createObjectBuilder()
                                                .field("name")
                                                .build()
                                        ).build()
                                ).build()
                        ).build()
                ).build();
        assertEquals(
                "user(name:\"k0kubun\") {\n" +
                        "  name\n" +
                        "  friends(first:10) {\n" +
                        "    totalCount\n" +
                        "    edges {\n" +
                        "      cursor\n" +
                        "      node {\n" +
                        "        name\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n",
                query);
    }

    @Test
    public void buildPaginatedObjects()
    {
        String query = GraphQL.createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), GraphQL.createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, "Y3Vyc29yMQ==", GraphQL.createObjectBuilder()
                                .field("totalCount")
                                .object("edges", GraphQL.createObjectBuilder()
                                        .field("cursor")
                                        .object("node", GraphQL.createObjectBuilder()
                                                .field("name")
                                                .build()
                                        ).build()
                                ).build()
                        ).build()
                ).build();
        assertEquals(
                "user(name:\"k0kubun\") {\n" +
                        "  name\n" +
                        "  friends(first:10 after:\"Y3Vyc29yMQ==\") {\n" +
                        "    totalCount\n" +
                        "    edges {\n" +
                        "      cursor\n" +
                        "      node {\n" +
                        "        name\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n",
                query);
    }

    @Test
    public void buildInlineFragment()
    {
        String query = GraphQL.createQueryBuilder()
                .object("node", GraphQL.createObjectBuilder()
                        .on("User", GraphQL.createObjectBuilder()
                                .field("id")
                                .build()
                        ).build()
                ).build();
        assertEquals(
                "node {\n" +
                        "  ... on User {\n" +
                        "    id\n" +
                        "  }\n" +
                        "}\n",
                query);
    }
}
