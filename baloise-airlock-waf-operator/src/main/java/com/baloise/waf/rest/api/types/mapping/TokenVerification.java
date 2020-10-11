
package com.baloise.waf.rest.api.types.mapping;

import java.util.List;

public class TokenVerification {

    public String type;
    public String jwsAlgorithm;
    public String jwsKey;
    public String jweAlgorithm;
    public String jweKey;
    public Boolean expiryCheckEnabled;
    public Integer expiryCheckSkew;
    public Boolean extractTechnicalClientIdEnabled;
    public String extractTechnicalClientIdName;
    public Boolean setAuditTokenFromSubjectEnabled;
    public List<Object> claimRestrictions = null;
    public List<Object> roleExtractions = null;

}
