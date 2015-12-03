package sese2015.g3.goldenlion.protocol.service;

import sese2015.g3.goldenlion.protocol.domain.ProtocolEntry;
import sese2015.g3.goldenlion.protocol.domain.ProtocolEntryType;

import java.util.List;

/**
 * Created by Mario on 02.12.2015.
 */
public interface ProtocolWriterService {
    List<ProtocolEntry> getAllProtocolEntries();

    void writeProtocolEntry(String protocolMessage, ProtocolEntryType protocolEntryType);
}
