package com.eazybank.cards.mapper;

import com.eazybank.cards.dto.CardsDto;
import com.eazybank.cards.entity.Cards;
import org.springframework.stereotype.Component;

import javax.smartcardio.Card;

@Component
public class CardMapper {

    public static CardsDto mapToCardDto(Cards cards, CardsDto cardsDto) {

        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cardsDto.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    public static Cards mapToCardEntity(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAmountAvailable(cardsDto.getAvailableAmount());
        return cards;
    }
}
