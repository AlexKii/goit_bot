package ua.dpw.users;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.dpw.AppLauncher;
import ua.dpw.currency.bank.Bank;
import ua.dpw.currency.currencies.Currency;
import ua.dpw.language.LanguageSwitcher;
import ua.dpw.notifications.Scheduler;
import ua.dpw.properties.ApplicationProperties;

public class UserService {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    public User createUser(long userId, String firstName, String lastName, String langCode,
        Map<String, String> language) {
        LOG.info("Add new user with id {} {} {}", userId, firstName, lastName);
        User user = new User();
        user.setUserId(userId);
        user.setUserName(firstName);
        user.setLastName(lastName);
        user.setDeltaHours(0);
        user.setAlertTime(100);
        user.setSymbolsAfterComma(AppLauncher.APPLICATION_PROPERTIES.getDecimalPrecision());
        user.setBank(AppLauncher.APPLICATION_PROPERTIES.getBank());
        user.getCurrencies().add(AppLauncher.APPLICATION_PROPERTIES.getCurrency());
        user.setLangCode(langCode);
        user.setLanguage(language);
        return user;
    }

    public void addUser(User user) {
        AppLauncher.APPLICATION_PROPERTIES.getUsers().put(user.getUserId(), user);
        ApplicationProperties.saveUsersListToFile();
    }

    public User getUserById(long userId) {
        User user = AppLauncher.APPLICATION_PROPERTIES.getUsers().get(userId);
        if  (user!=null){
            user.setLanguage(LanguageSwitcher.setLanguageMap(user.getLangCode()));
        }
        return user;
    }

    public void updateUser(User user, Bank bank) {
        user.setBank(bank);
        addUser(user);
    }

    public void updateUser(User user, Currency currency) {
        List<Currency> userCurrencyList = user.getCurrencies();
        if (userCurrencyList.contains(currency)) {
            userCurrencyList.remove(currency);
        } else {
            userCurrencyList.add(currency);
        }
        addUser(user);
    }

    public void updateUserTimeCommand (User user, int value) {
            user.setDeltaHours(value - Scheduler.getCurrentHour());
            addUser(user);
    }
    public void updateUserSymbolsAfterComma (User user, int value) {
        user.setSymbolsAfterComma(value);
        addUser(user);
    }

    public void updateUserAlertTime (User user, int value) {
        user.setAlertTime(value);
        addUser(user);
    }
}


