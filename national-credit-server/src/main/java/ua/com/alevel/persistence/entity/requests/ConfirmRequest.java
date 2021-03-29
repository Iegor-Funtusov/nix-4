package ua.com.alevel.persistence.entity.requests;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Client;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "confirm_request")
public class ConfirmRequest extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "client_from")
    private Client clientFrom;

    @ManyToOne
    @JoinColumn(name = "client_to")
    private Client clientTo;

    @ManyToOne
    private ProposalRequest request;

    public ConfirmRequest() {
        super();
    }
}
