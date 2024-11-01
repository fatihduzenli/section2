package com.eazybank.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
/**
 * This class provides the implementation for the {@link AuditorAware} interface, which is used to retrieve the current auditor for audit logging purposes.
 *
 * @author Fatih Düzenli
 * @since 1.0
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> { //The AuditorAware interface is a generic interface that takes a type parameter. This type parameter specifies the type of the current auditor. In this case, the current auditor is represented as a string value.
    /**
     * Retrieves the current auditor as a string value. In this case, it always returns "ACCOUNTS_MS" as the auditor.
     *
     * @return an {@link Optional} containing the current auditor as a string value.
     */
    @Override
    public Optional<String> getCurrentAuditor() { //The getCurrentAuditor method is implemented to return an Optional object containing the current auditor as a string value. In this case, it always returns "ACCOUNTS_MS" as the auditor.
        return Optional.of("ACCOUNTS_MS");
    }
}
/*
1. Create a new class named AuditAwareImpl in the audit package.
2. Implement the AuditorAware<String> interface in the AuditAwareImpl class.
3. Implement the getCurrentAuditor method to return an Optional object containing the current auditor as a string value ("ACCOUNTS_MS").
4. Annotate the class with @Component("auditorAwareImpl") to mark it as a Spring component with the specified bean name.
5. in BaseEntity.java, add the @EntityListeners(AuditingEntityListener.class) annotation to specify that the AuditAwareImpl class should be used as the entity listener for auditing purposes.
6. In AccountsApplication.java, add the @EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl") annotation to enable JPA auditing and specify the bean name of the AuditorAware implementation.
 */