package com.soukaina.commande.email;

import lombok.Getter;

public enum EmailTemplate {
    ORDER_CONFIRMATION("order-confirmation.html", "Payment successfully processed")
    ;

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplate(final String template, final String subject) {
        this.template = template;
        this.subject = subject;
    }
}
