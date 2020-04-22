package com.alexa.oms.jsonpojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Claims
{
    private String user_name;

    private String exp;

    private List<String> scope;

    private List<String> authorities;

    private String jti;

    private String client_id;

    public String getUser_name()
    {
        return user_name;
    }

    public void setUser_name(String user_name)
    {
        this.user_name = user_name;
    }

    public String getExp()
    {
        return exp;
    }

    public void setExp(String exp)
    {
        this.exp = exp;
    }

    public List<String> getScope()
    {
        return scope;
    }

    public void setScope(List<String> scope)
    {
        this.scope = scope;
    }

    public List<String> getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(List<String> authorities)
    {
        this.authorities = authorities;
    }

    public String getJti()
    {
        return jti;
    }

    public void setJti(String jti)
    {
        this.jti = jti;
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user_name = " + user_name + ", exp = " + exp + ", scope = " + scope + ", authorities = " + authorities + ", jti = " + jti + ", client_id = " + client_id + "]";
    }
}