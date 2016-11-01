package com.share.jack.jpushdemo.basehttp;

/**
 *
 */
public class HttpServletAddress {

    private static final class SingletonHolder {
        private static final HttpServletAddress INSTANCE = new HttpServletAddress();
    }

    public static HttpServletAddress getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private HttpServletAddress() {
    }

    private String mOnlineAddress;

    public String getOnlineAddress() {
        return mOnlineAddress;
    }

    public void setOnlineAddress(String mOnlineAddress) {
        this.mOnlineAddress = mOnlineAddress;
    }

    public String getAddress() {
        return mOnlineAddress;
    }
}
