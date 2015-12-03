package sese2015.g3.goldenlion.protocol.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.protocol.domain.ProtocolEntry;
import sese2015.g3.goldenlion.protocol.service.ProtocolWriterService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/protocol")
public class ProtocolResource {
    private Log log = LogFactory.getLog(getClass());

    private ProtocolWriterService protocolWriterService;

    @Autowired
    public ProtocolResource(ProtocolWriterService protocolWriterService) {
        this.protocolWriterService = protocolWriterService;
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    @PreAuthorize("hasAuthority('ADM')")
    public List<ProtocolEntry> list() {
        return this.protocolWriterService.getAllProtocolEntries();
    }
}
