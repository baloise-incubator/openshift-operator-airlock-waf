
package com.baloise.waf.rest.api.client.beans.vhost;

import java.util.List;

public class Tls {

    public Boolean lowStrengthCiphersAllowed;
    public Boolean ocspStaplingEnabled;
    public Boolean letsEncryptEnabled;
    public String clientCertificateAuthentication;
    public Integer chainVerificationDepth;
    public Boolean ocspValidationEnforced;
    public List<Object> caCertificatesForClientCertificateSelection = null;
    public List<Object> caCertificatesForChainAndOcspValidation = null;

}
