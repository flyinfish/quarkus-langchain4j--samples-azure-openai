# chatbot

azurified from https://github.com/quarkiverse/quarkus-langchain4j/tree/main/samples/chatbot

## Running the example

copy [.env-template](.env-template) to [.env](.env) to connect to your azure-resources.

Then, simply run the project in Dev mode:

```
mvn quarkus:dev
```

## Using the example

Open your browser and navigate to http://localhost:8080. Click the red robot
in the bottom right corner to open the chat window.

The chatbot is a conversational agent that uses information from the files
in `src/main/resources/catalog` to answer your questions about banking
products. More information about how it works is shown on the webpage.
