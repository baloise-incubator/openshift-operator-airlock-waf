
package com.baloise.waf.rest.api.client.beans.mapping;

import java.util.List;

public class Access {

    public DeniedUrl deniedUrl;
    public List<Object> restrictions = null;
    public String clientCertificateAuthentication;
    public String authenticationFlow;
    public String backendLogoutUrl;
    public Boolean ntlmPassthroughEnabled;
    public CredentialsPropagation credentialsPropagation;
    public Boolean tokensEnabled;
    public TokenTransport tokenTransport;
    public TokenVerification tokenVerification;

}
