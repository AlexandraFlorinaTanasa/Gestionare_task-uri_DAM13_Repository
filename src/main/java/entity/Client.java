package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor

public class Client {

   private Integer idClient;
    private String numeClient;
    private String email;
     private String adresa;

    public Client(Integer idClient, String numeClient, String email, String adresa) {
        this.idClient = idClient;
        this.numeClient = numeClient;
        this.email = email;
        this.adresa = adresa;
    }

    public Client(Integer idClient, String numeClient) {
        this.idClient = idClient;
        this.numeClient = numeClient;
    }
}
