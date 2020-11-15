package com.github.k0kubun.builder.query.graphql.builder;

import com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryBuilderTest
{
    @Test
    public void buildEmptyQuery()
    {
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .build();
        assertEquals("", query);
    }

    @Test
    public void buildFields()
    {
        String query = GraphQLQueryBuilder.createQueryBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("query", GraphQLQueryBuilder.createObjectBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("query", GraphQLQueryBuilder.createObjectBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("query", GraphQLQueryBuilder.createObjectBuilder()
                        .field("hello")
                        .object("user", GraphQLQueryBuilder.createObjectBuilder()
                                .field("world")
                                .field("the")
                                .object("nested", GraphQLQueryBuilder.createObjectBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("user", ImmutableMap.of("login", "k0kubun"), GraphQLQueryBuilder.createObjectBuilder()
                        .object("repository", ImmutableMap.of("name", "hamlit", "foo", 100), GraphQLQueryBuilder.createObjectBuilder()
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

    @Test
    public void buildObjects()
    {
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), GraphQLQueryBuilder.createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, GraphQLQueryBuilder.createObjectBuilder()
                                .field("totalCount")
                                .object("edges", GraphQLQueryBuilder.createObjectBuilder()
                                        .field("cursor")
                                        .object("node", GraphQLQueryBuilder.createObjectBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), GraphQLQueryBuilder.createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, "Y3Vyc29yMQ==", GraphQLQueryBuilder.createObjectBuilder()
                                .field("totalCount")
                                .object("edges", GraphQLQueryBuilder.createObjectBuilder()
                                        .field("cursor")
                                        .object("node", GraphQLQueryBuilder.createObjectBuilder()
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
        String query = GraphQLQueryBuilder.createQueryBuilder()
                .object("node", GraphQLQueryBuilder.createObjectBuilder()
                        .on("User", GraphQLQueryBuilder.createObjectBuilder()
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
