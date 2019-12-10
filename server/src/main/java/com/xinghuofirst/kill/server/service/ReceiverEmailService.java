package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.server.dto.PersonAndActivity;

public interface ReceiverEmailService {

    void consumeEmailMsg(PersonAndActivity info);
}
