package com.eazybank.cards.repository;

import com.eazybank.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.smartcardio.Card;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {
    Optional<Cards>findByMobileNumber(String mobileNumber);
    Optional<Cards>findByCardNumber(String cardNumber);
}
