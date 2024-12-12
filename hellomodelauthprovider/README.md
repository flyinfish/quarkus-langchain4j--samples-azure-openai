# helloworld using `io.quarkiverse.langchain4j.auth.ModelAuthProvider`

most simple example 
does not user `api-key` but `io.quarkiverse.langchain4j.auth.ModelAuthProvider` providing a token

## Running the example

copy [.env-template](.env-template) to [.env](.env) to connect to your azure-resources.

Then, simply run the project in Dev mode:

```
mvn quarkus:dev
```

open http://localhost:8080/hello or invoke rest with `curl localhost:8080/hello` or using swagger-ui

