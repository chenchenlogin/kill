package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.model.entity.Person;

public interface ReceiverEmailService {

    void consumeEmailMsg(Person info);
}
