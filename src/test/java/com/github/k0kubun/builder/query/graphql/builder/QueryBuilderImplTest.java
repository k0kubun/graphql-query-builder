package com.github.k0kubun.builder.query.graphql.builder;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.createObjectBuilder;
import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.createQueryBuilder;
import static org.junit.Assert.assertEquals;

public class QueryBuilderImplTest
{
    @Test
    public void buildEmptyQuery()
    {
        String query = createQueryBuilder()
                .build();
        assertEquals("", query);
    }

    @Test
    public void buildFields()
    {
        String query = createQueryBuilder()
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
        String query = createQueryBuilder()
                .object("query", createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("query", createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("query", createObjectBuilder()
                        .field("hello")
                        .object("user", createObjectBuilder()
                                .field("world")
                                .field("the")
                                .object("nested", createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("user", ImmutableMap.of("login", "k0kubun"), createObjectBuilder()
                        .object("repository", ImmutableMap.of("name", "\"name\"", "foo", 100), createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, createObjectBuilder()
                                .field("totalCount")
                                .object("edges", createObjectBuilder()
                                        .field("cursor")
                                        .object("node", createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("user", ImmutableMap.of("name", "k0kubun"), createObjectBuilder()
                        .field("name")
                        .objects("friends", 10, "Y3Vyc29yMQ==", createObjectBuilder()
                                .field("totalCount")
                                .object("edges", createObjectBuilder()
                                        .field("cursor")
                                        .object("node", createObjectBuilder()
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
        String query = createQueryBuilder()
                .object("node", createObjectBuilder()
                        .on("User", createObjectBuilder()
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
