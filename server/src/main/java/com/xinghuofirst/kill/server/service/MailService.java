package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.server.dto.MailDto;

public interface MailService {
    void sendHTMLMail(final MailDto dto);
}
