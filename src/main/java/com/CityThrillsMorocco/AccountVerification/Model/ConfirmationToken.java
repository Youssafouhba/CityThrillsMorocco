package com.CityThrillsMorocco.AccountVerification.Model;

import com.CityThrillsMorocco.Client.Model.Client;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="confirmationToken")
@Data
public class ConfirmationToken {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "token_id")
        private Long tokenId;

        @Column(name = "confirmation_token")
        private String confirmationToken;

        @Temporal(TemporalType.TIMESTAMP)
        private Date createdDate;

        @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        private Client user;

        public ConfirmationToken() {
        }

        public ConfirmationToken(Client user) {
            this.user = user;
            createdDate = new Date();
            confirmationToken = UUID.randomUUID().toString();
        }

}
