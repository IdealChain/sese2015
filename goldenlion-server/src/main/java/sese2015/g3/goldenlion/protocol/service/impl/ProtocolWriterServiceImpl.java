package sese2015.g3.goldenlion.protocol.service.impl;

import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.protocol.domain.ProtocolEntry;
import sese2015.g3.goldenlion.protocol.domain.ProtocolEntryType;
import sese2015.g3.goldenlion.protocol.repository.ProtocolEntryRepository;
import sese2015.g3.goldenlion.protocol.service.ProtocolWriterService;
import sese2015.g3.goldenlion.security.domain.User;

import java.util.List;

/**
 * Created by Mario on 02.12.2015.
 */
@Aspect
@Service
public class ProtocolWriterServiceImpl implements ProtocolWriterService {
    private ProtocolEntryRepository protocolEntryRepository;

    @Autowired
    public ProtocolWriterServiceImpl(ProtocolEntryRepository protocolEntryRepository) {
        this.protocolEntryRepository = protocolEntryRepository;
    }

    @Before("execution(* sese2015.g3.goldenlion..*Repository.*(..)) && !bean(protocolEntryRepository)")
    public void logBeforeDatabaseAccess(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            protocolEntryRepository.save(new ProtocolEntry(user, "User " + user.getUsername() + " calls " + joinPoint.toShortString(), ProtocolEntryType.DATABASE));
        }
    }

    @After("execution(* sese2015.g3.goldenlion..*Repository.*(..)) && !bean(protocolEntryRepository)")
    public void logAfterDatabaseAccess(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            protocolEntryRepository.save(new ProtocolEntry(user, "User " + user.getUsername() + " called " + joinPoint.toShortString(), ProtocolEntryType.DATABASE));
        }
    }

    @Override
    public List<ProtocolEntry> getAllProtocolEntries() {
        return Lists.newArrayList(protocolEntryRepository.findAll(new Sort(Sort.Direction.DESC, "logWriteTime")));
    }

    @Override
    public void writeProtocolEntry(String protocolMessage, ProtocolEntryType protocolEntryType) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            protocolEntryRepository.save(new ProtocolEntry(user, protocolMessage, protocolEntryType));
        }
    }
}
