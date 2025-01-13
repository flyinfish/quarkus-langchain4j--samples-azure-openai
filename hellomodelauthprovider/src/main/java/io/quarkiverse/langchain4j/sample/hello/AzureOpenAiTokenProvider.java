package io.quarkiverse.langchain4j.sample.hello;

import static java.lang.String.format;

import com.azure.core.credential.TokenRequestContext;
import com.azure.core.implementation.AccessTokenCache;
import com.azure.identity.DefaultAzureCredentialBuilder;
import io.quarkiverse.langchain4j.auth.ModelAuthProvider;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mutiny.zero.flow.adapters.AdaptersToFlow;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AzureOpenAiTokenProvider implements ModelAuthProvider {
  private static final String SCOPE = "https://cognitiveservices.azure.com/.default";
  private static final TokenRequestContext TOKEN_REQUEST_CONTEXT =
      new TokenRequestContext().addScopes(SCOPE);

  private AccessTokenCache tokenCache;
  @Inject Logger logger;

  @PostConstruct
  void buildTokenCache() {
    // use default credential chain trying whathever available, hopefully grab a token at last with
    // AzureCliCredential
    var credentialChain = new DefaultAzureCredentialBuilder().build();
    tokenCache = new AccessTokenCache(credentialChain);
  }

  @Override
  public String getAuthorization(Input input) {
    throw new UnsupportedOperationException("This method should not be called");
  }

  @Override
  public Uni<String> getAuthorizationAsync(Input input) {
    var publisher = AdaptersToFlow.publisher(tokenCache.getToken(TOKEN_REQUEST_CONTEXT, false));
    return Uni.createFrom()
        .publisher(publisher)
        .onItem()
        .transform(token -> format("%s %s", token.getTokenType(), token.getToken()));
  }
}
