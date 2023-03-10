package org.example.telegrambots.currency.menus;

import org.example.currency.currencies.Currency;
import org.example.telegrambots.bot.services.UserMessage;
import org.example.telegrambots.currency.commands.Commands;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMenu {
    public InlineKeyboardMarkup createMenu(UserMessage userMessage) {
        Currency currency = userMessage.getUser().getCurrency();
        String markEmoji = "✔";

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton((currency.equals(Commands.CURRENCY_USD) ? markEmoji : "") + Commands.CURRENCY_USD.getButtonUkrText());
        button2.setCallbackData(Commands.CURRENCY_USD.toString());
        row1.add(button2);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton((currency.equals(Commands.CURRENCY_EUR) ? markEmoji : "") + Commands.CURRENCY_EUR.getButtonUkrText());
        button3.setCallbackData(Commands.CURRENCY_EUR.toString());
        row2.add(button3);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton buttonBack = new InlineKeyboardButton(Commands.BACK.getButtonUkrText());
        buttonBack.setCallbackData(Commands.MAIN_OPTIONS.toString());
        row3.add(buttonBack);

        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
