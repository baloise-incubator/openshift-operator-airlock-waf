
package com.baloise.waf.rest.api.types.vhost;

import java.util.List;

public class Attributes {

    public String name;
    public String tenant;
    public String hostName;
    public List<Object> aliasNames = null;
    public Boolean showMaintenancePage;
    public Boolean strictlyMatchFullyQualifiedDomainName;
    public Integer keepAliveTimeout;
    public Boolean encodedSlashesAllowed;
    public Boolean downloadPdfsAsAttachmentsEnforced;
    public String serverAdmin;
    public String defaultRedirect;
    public List<Object> pathRedirects = null;
    public NetworkInterface networkInterface;
    public Tls tls;
    public Session session;
    public ExpertSettings expertSettings;

}
