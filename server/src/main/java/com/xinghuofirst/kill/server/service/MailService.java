package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.server.dto.MailDto;
import com.xinghuofirst.kill.server.dto.PersonAndActivity;

public interface MailService {
    void sendHTMLMail(final MailDto dto);


}
