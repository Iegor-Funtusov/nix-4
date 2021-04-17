package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.com.alevel.persistence.entity.user.Client;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class Profile {

    private Optional<Client> client;

    public void setClient(Client client) {
        this.client = Optional.of(client);
    }


    public Profile(Client client) {

    }
}
