
package com.baloise.waf.rest.api.types.mapping;


public class Access_ {

    public DeniedUrl_ deniedUrl;
    public Boolean restrictions;
    public Boolean clientCertificateAuthentication;
    public Boolean authenticationFlow;
    public Boolean backendLogoutUrl;
    public Boolean ntlmPassthroughEnabled;
    public CredentialsPropagation_ credentialsPropagation;
    public Boolean tokensEnabled;
    public TokenVerification_ tokenVerification;
    public TokenTransport_ tokenTransport;

}
