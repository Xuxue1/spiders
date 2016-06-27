package com.xuxue.spider.core.util;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HanHan on 2016/6/23.
 */
public class SpiderCredentialsProvider  implements CredentialsProvider {

    private final ConcurrentHashMap<AuthScope,Credentials> credentials;

    public SpiderCredentialsProvider(){
        super();
        credentials= new ConcurrentHashMap<AuthScope, Credentials>();
    }

    @Override
    public void setCredentials(final AuthScope authscope, final Credentials credentials) {
        Args.NotNULL(authscope);
        Args.NotNULL(credentials);
        this.credentials.put(authscope,credentials);
    }

    @Override
    public Credentials getCredentials(final AuthScope authscope) {
        return matchCredentials(this.credentials, authscope);
    }

    @Override
    public void clear() {
        credentials.clear();
    }

    public void remove(final AuthScope authScope){
        credentials.remove(authScope);
    }

    private static Credentials matchCredentials(
            final Map<AuthScope, Credentials> map,
            final AuthScope authscope) {
        // see if we get a direct hit
        Credentials creds = map.get(authscope);
        System.out.println("creds is null"+(creds==null));
        if (creds == null) {
            // Nope.
            // Do a full scan
            int bestMatchFactor  = -1;
            AuthScope bestMatch  = null;
            for (final AuthScope current: map.keySet()) {
                final int factor = authscope.match(current);
                if (factor > bestMatchFactor) {
                    bestMatchFactor = factor;
                    bestMatch = current;
                }
            }
            if (bestMatch != null) {
                creds = map.get(bestMatch);
            }
        }

        System.out.println("creds is null"+(creds==null));

        return creds;
    }
}
