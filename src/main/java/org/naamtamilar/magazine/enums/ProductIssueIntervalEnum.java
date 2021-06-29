package org.naamtamilar.magazine.enums;

public enum ProductIssueIntervalEnum {

    WEEKLY("weekly"),
    MONTHLY( "monthly"),
    YEARLY("yearly"),
    DAILY("daily");
    private String issueInterval;

    ProductIssueIntervalEnum(String issueInterval) {
       this.issueInterval=issueInterval;
    }

    public String getIssueInterval() {
        return issueInterval;
    }
}