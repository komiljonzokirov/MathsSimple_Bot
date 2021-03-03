package com.company.komzak;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.ArrayList;
import java.util.List;

public class ExampleBot extends TelegramLongPollingBot {
    String BOT_USERNAME = "TESTBOTTA_bot";
    String BOT_TOKEN = "1616013890:AAHlrUxl4VSHIktZgF_4uUaA50-7iAN_YWU";
    private List<Account> accountList = new ArrayList<>();

    private MathService mathService = new MathServiceImpl();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.hasText()) {
                    String text = message.getText();

                    if (text.equals("/start")) {
                        execute(sendMsg(message));
                    } else {
                        int index = -1;
                        for (int i = 0; i < accountList.size(); i++) {
                            Account account = accountList.get(i);
                            if (account.getUser().getId().equals(message.getFrom().getId())) {
                                index = i;
                                break;
                            }
                        }
                        Account account = accountList.get(index);
                        int myResult = Integer.parseInt(text);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText(String.valueOf(mathService.isCorrect(account, myResult)));
                        execute(sendMessage);

                        execute(sendMsg(message));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SendMessage sendMsg(Message message) {
        Account account;
        int index = -1;
        for (int i = 0; i < accountList.size(); i++) {
            Account account1 = accountList.get(i);
            if (account1.getUser().getId().equals(message.getFrom().getId())) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            account = new Account(message.getFrom(), 1);
            accountList.add(account);
        } else {
            account = accountList.get(index);
        }
        String s = mathService.generateMath(account);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(s);
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
