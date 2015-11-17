package sese2015.g3.goldenlion.security.domain;

import sese2015.g3.goldenlion.commons.entity.PersistentObject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRole extends PersistentObject {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_users"))
    private User user;

    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role name) {
        this.role = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
                role == userRole.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
