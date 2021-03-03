package com.company.komzak;

import org.telegram.telegrambots.api.objects.User;

import java.util.Objects;

public class Account {

    private User user;
    private int step;
    private int result;

    public Account(User user, int step) {
        this.user = user;
        this.step = step;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", step=" + step +
                '}';
    }
}
