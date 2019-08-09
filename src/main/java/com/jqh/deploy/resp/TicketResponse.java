package com.jqh.deploy.resp;

public class TicketResponse {
    /**
     * 错误码，详情见微信官方文档"
     */
    private int errcode;
    /**
     * 错误信息，详情见微信官方文档
     */
    private String errmsg;
    private String ticket;
    private long expires_in;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
