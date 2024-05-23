package com.CityThrillsMorocco.accountverification.Model;

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

        @OneToOne
        @JoinColumn(name = "user_id", unique = true)
        private User user;

        @Column(name = "confirmation_token")
        private String confirmationToken;

        @Temporal(TemporalType.TIMESTAMP)
        private Date createdDate;

        public ConfirmationToken() {
        }

        public ConfirmationToken(User user) {
                this.user = user;
                createdDate = new Date();
                confirmationToken = UUID.randomUUID().toString();
        }

}
