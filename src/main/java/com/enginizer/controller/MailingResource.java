/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.enginizer.controller;

import com.enginizer.exception.MailException;
import com.enginizer.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MailingResource {
    private static final Logger LOG = LoggerFactory.getLogger(MailingResource.class);

    @Autowired
    @Qualifier("gmailMailSender")
    private com.enginizer.service.MailSender mailSender;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @CrossOrigin(origins = {"http://localhost:80","http://uc8.co:80","http://uc8.co","http://localhost"})
    public ResponseEntity<String> sendEMAIL(@ModelAttribute("email") Email email) {
        try{
            mailSender.sendEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (MailException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.valueOf(e.getStatus()));
        }
    }

}
