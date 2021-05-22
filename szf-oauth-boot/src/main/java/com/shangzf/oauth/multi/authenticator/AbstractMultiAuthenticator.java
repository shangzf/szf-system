package com.shangzf.oauth.multi.authenticator;

import com.shangzf.oauth.multi.MultiAuthentication;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractMultiAuthenticator implements MultiAuthenticator {

    @Override
    public void prepare(MultiAuthentication multiAuthentication, HttpServletResponse response) {

    }

    @Override
    public void complete(MultiAuthentication multiAuthentication) {

    }
}
