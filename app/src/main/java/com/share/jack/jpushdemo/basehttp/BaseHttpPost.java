package com.share.jack.jpushdemo.basehttp;



/**
 *
 */
public abstract class BaseHttpPost {

    protected abstract String getUri();

    protected String getDomain() {
        return HttpServletAddress.getInstance().getAddress();
    }

    protected String getUrl() {
        return getDomain() + getUri();
    }
}
