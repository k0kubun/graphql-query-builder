# graphql-query-builder [![Build Status](https://travis-ci.org/k0kubun/graphql-query-builder.svg?branch=master)](https://travis-ci.org/k0kubun/graphql-query-builder)

GraphQL query builder for Java

## Installation

### Maven (pom.xml)

```xml
<dependencies>
    <dependency>
        <groupId>com.github.k0kubun</groupId>
        <artifactId>graphql-query-builder</artifactId>
        <version>0.4.1</version>
    </dependency>
</dependencies>
```

### Gradle (build.gradle)

```groovy
dependencies {
    compile 'com.github.k0kubun:graphql-query-builder:0.4.1'
}
```

## Usage

To build following query:

```graphql
user(name:"k0kubun") {
  name
  friends(first:10 after:"Y3Vyc29yMQ==") {
    totalCount
    edges {
      cursor
      node {
        ... on User {
          name
        }
      }
    }
  }
}
```

You can write code in the following way:

```java
import com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder;
import java.util.HashMap;
import java.util.Map;

public class Example {
    
    public example() {
    Map<String, Object> userParams =
                    Map.of("name", "k0kubun");
    
            String query = GraphQLQueryBuilder.query()
                    .object("user", userParams, GraphQLQueryBuilder.object()
                            .field("name")
                            .objects("friends", 10, "Y3Vyc29yMQ==", GraphQLQueryBuilder.object()
                                    .field("totalCount")
                                    .object("edges", GraphQLQueryBuilder.object()
                                            .field("cursor")
                                            .object("node", GraphQLQueryBuilder.object()
                                                    .on("User", GraphQLQueryBuilder.object()
                                                            .field("name")
                                                            .build()
                                                    ).build()
                                            ).build()
                                    ).build()
                            ).build()
                    ).build();
    }
}
```

## Project Status

Experimental.

### Unsupported features

- `@include`
- `@skip`
- Mutation
- Fragments
- Variables
- Aliases

## Code style

Airlift 
* (https://github.com/airlift/codestyle)

## Release

Create `~/.gradle/gradle.properties` like:

```
sonatypeUsername=k0kubun
sonatypePassword=PASSWORD
signing.keyId=KEYID
signing.password=PASSWORD
signing.secretKeyRingFile=/Users/k0kubun/.gnupg/secring.gpg
```

KEYID is the _last_ 8 characters of what's shown at `gpg -K`.

Then run:

```
$ ./gradlew uploadArchives
```

At https://oss.sonatype.org/#stagingRepositories, push "Close" and then "Release".
Sync to maven repository might take some time.

## License

MIT License
