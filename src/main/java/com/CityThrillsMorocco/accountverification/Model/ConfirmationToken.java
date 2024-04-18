package com.CityThrillsMorocco.accountverification.Model;

import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.user.model.User;
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

        @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        private User user;

        @OneToOne(targetEntity = Agence.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "agence_id")
        private Agence agence;

        public ConfirmationToken() {
        }

        public ConfirmationToken(User user) {
            this.user = user;
            createdDate = new Date();
            confirmationToken = UUID.randomUUID().toString();
        }

        public ConfirmationToken(Agence agence) {
                this.agence = agence;
                createdDate = new Date();
                confirmationToken = UUID.randomUUID().toString();
        }

}
