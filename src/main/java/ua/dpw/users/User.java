package ua.dpw.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.dpw.currency.bank.Bank;
import ua.dpw.currency.currencies.Currency;
import ua.dpw.language.LanguageSwitcher;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements Serializable {

    private long userId;
    private String userName;
    private String lastName;
    private int symbolsAfterComma;
    private List<Currency> currencies = new ArrayList<>();
    private Bank bank;
    private int deltaHours;
    private int alertTime;
    private String langCode;
    private transient Map<String, String> language;
    private boolean newUser;

    public void setLangCode(String langCode) {
        this.langCode = langCode;
        setLanguage(LanguageSwitcher.setLanguageMap(langCode));
    }
}
