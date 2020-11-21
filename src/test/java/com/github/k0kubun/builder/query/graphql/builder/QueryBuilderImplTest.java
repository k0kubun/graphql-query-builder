package com.github.k0kubun.builder.query.graphql.builder;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.object;
import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.query;
import static org.junit.Assert.assertEquals;

public class QueryBuilderImplTest
{
    @Test
    public void buildEmptyQuery()
    {
        String query = query()
                .build();
        assertEquals("", query);
    }

    @Test
    public void buildFields()
    {
        String query = query()
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
        String query = query()
                .object("query", object()
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
        String query = query()
                .object("query", object()
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
        String query = query()
                .object("query", object()
                        .field("hello")
                        .object("user", object()
                                .field("world")
                                .field("the")
                                .object("nested", object()
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
        String query = query()
                .object("user", ImmutableMap.of("login", "k0kubun"), object()
                        .object("repository", ImmutableMap.of("name", "\"name\"", "foo", 100), object()
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
        String query = query()
                .object("user", ImmutableMap.of("name", "k0kubun"), object()
                        .field("name")
                        .objects("friends", 10, object()
                                .field("totalCount")
                                .object("edges", object()
                                        .field("cursor")
                                        .object("node", object()
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
        String query = query()
                .object("user", ImmutableMap.of("name", "k0kubun"), object()
                        .field("name")
                        .objects("friends", 10, "Y3Vyc29yMQ==", object()
                                .field("totalCount")
                                .object("edges", object()
                                        .field("cursor")
                                        .object("node", object()
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
        String query = query()
                .object("node", object()
                        .on("User", object()
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
