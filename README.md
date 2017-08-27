# graphql-query-builder

GraphQL query builder for Java

## Usage

To build following query,

```graphql
user(name: "k0kubun") {
  name
  friends(first:10 after:"Y3Vyc29yMQ==") {
    totalCount
    edges {
      cursor
      node {
        name
      }
    }
  }
}
```

you can write code in the following way.

```java
import com.github.k0kubun.builder.query.graphql.GraphQL;
import java.util.HashMap;
import java.util.Map;

Map<String, String> userParams = new HashMap<>();
userParams.put("name", "k0kubun");

String query = GraphQL.createQueryBuilder()
    .object("user", userParams, GraphQL.createObjectBuilder()
        .field("name")
        .objects("friends", 10, "Y3Vyc29yMQ==", GraphQL.createObjectBuilder()
            .field("totalCount")
            .object("edges", GraphQL.createObjectBuilder()
                .field("cursor")
                .object("node", GrpahQL.createObjectBuilder()
                    .field("name")
                    .build()
                ).build()
            ).build()
        ).build()
    ).build();
```

## Project Status

Experimental.

### TODO

Following things are not supported yet.

- Inline Fragment
- `@include`
- `@skip`
- Mutation
- Fragments
- Variables
- Aliases

## License

MIT License
