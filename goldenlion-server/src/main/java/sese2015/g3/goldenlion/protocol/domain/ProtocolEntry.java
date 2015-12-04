package sese2015.g3.goldenlion.protocol.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;
import sese2015.g3.goldenlion.security.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Mario on 02.12.2015.
 */
@Entity
@Table(name = "protocolentries")
public class ProtocolEntry extends PersistentObject {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProtocolEntryType protocolEntryType;

    @NotNull
    private LocalDateTime logWriteTime;

    @NotNull
    @Lob
    private String protocolMessage;

    public ProtocolEntry() {
    }

    public ProtocolEntry(User user, String protocolMessage, ProtocolEntryType protocolEntryType) {
        this.user = user;
        this.protocolMessage = protocolMessage;
        this.protocolEntryType = protocolEntryType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLogWriteTime() {
        return logWriteTime;
    }

    public void setLogWriteTime(LocalDateTime logWriteTime) {
        this.logWriteTime = logWriteTime;
    }

    public ProtocolEntryType getProtocolEntryType() {
        return protocolEntryType;
    }

    public void setProtocolEntryType(ProtocolEntryType protocolEntryType) {
        this.protocolEntryType = protocolEntryType;
    }

    public String getProtocolMessage() {
        return protocolMessage;
    }

    public void setProtocolMessage(String protocolMessage) {
        this.protocolMessage = protocolMessage;
    }

    @PrePersist
    protected void onCreate() {
        logWriteTime = LocalDateTime.now();
    }
}
