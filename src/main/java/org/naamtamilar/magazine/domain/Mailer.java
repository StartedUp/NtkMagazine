package org.naamtamilar.magazine.domain;

import lombok.Data;

@Data
public class Mailer {
    private String subject;
    private String[] recipients;
    private String[] bccList;
    private String[] ccList;
    private String body;
}