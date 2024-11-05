package com.eazybank.cards.service.impl;

import com.eazybank.cards.constants.CardsConstants;
import com.eazybank.cards.dto.CardsDto;
import com.eazybank.cards.entity.Cards;
import com.eazybank.cards.exception.CardAlreadyExistException;
import com.eazybank.cards.exception.ResourceNotFoundException;
import com.eazybank.cards.mapper.CardMapper;
import com.eazybank.cards.repository.CardsRepository;
import com.eazybank.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardsService {
    private final CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            throw new CardAlreadyExistException("Card already exists for the given mobile number " + mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAmountAvailable(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number ", mobileNumber)
        );
        return CardMapper.mapToCardDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number ", cardsDto.getCardNumber())
        );
        CardMapper.mapToCardEntity(cardsDto,new Cards());
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number ", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
