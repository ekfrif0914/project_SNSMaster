package com.codemaster.project_snsmaster.service;

public interface IF_EmailService {


    public String sendSimpleMessage(String to) throws Exception;

    public String sendPwMessage(String to,String userPW) throws Exception;
}
