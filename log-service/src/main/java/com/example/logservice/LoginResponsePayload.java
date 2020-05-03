package com.example.logservice;

class LoginResponsePayload {

    String uuid;
    String jwt;

    public LoginResponsePayload() {
    }

    public LoginResponsePayload(String uuid, String jwt) {
        this.uuid = uuid;
        this.jwt = jwt;
    }

    public LoginResponsePayload(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "LoginPayload{" +
                "uuid='" + uuid + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
