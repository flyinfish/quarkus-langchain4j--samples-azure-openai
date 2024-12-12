package io.quarkiverse.langchain4j.sample.hello;

import static java.lang.String.format;

import com.azure.core.credential.TokenRequestContext;
import com.azure.core.implementation.AccessTokenCache;
import com.azure.identity.DefaultAzureCredentialBuilder;
import io.quarkiverse.langchain4j.auth.ModelAuthProvider;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AzureOpenAiTokenProvider implements ModelAuthProvider {
  private static final String SCOPE = "https://cognitiveservices.azure.com/.default";
  private static final TokenRequestContext TOKEN_REQUEST_CONTEXT =
      new TokenRequestContext().addScopes(SCOPE);

  private AccessTokenCache tokenCache;

  @PostConstruct
  void buildTokenCache() {
    // use default credential chain trying whathever available, hopefully grab a token at last with AzureCliCredential
    var credentialChain = new DefaultAzureCredentialBuilder().build();
    tokenCache = new AccessTokenCache(credentialChain);
  }

  @Override
  public String getAuthorization(Input input) {
    var token = tokenCache.getTokenSync(TOKEN_REQUEST_CONTEXT, false).getToken();
    return format("Bearer %s", token);
  }
}
