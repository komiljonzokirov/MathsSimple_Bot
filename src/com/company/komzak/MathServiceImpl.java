package com.company.komzak;

import java.util.Random;

public class MathServiceImpl implements MathService {

    private Random random = new Random();

    @Override
    public String generateMath(Account account) {
        int number1 = random.nextInt(10);
        int number2 = random();
        int amal = random.nextInt(4);

        int result = 0;

        String str = null;
        switch (amal) {
            case 0:
                str = number1 + "+" + number2 + "=";
                result = number1 + number2;
                break;
            case 1:
                str = number1 + "-" + number2 + "=";
                result = number1 - number2;
                break;
            case 2:
                str = number1 + "*" + number2 + "=";
                result = number1 * number2;
                break;
            case 3:
                str = number1 + "/" + number2 + "=";
                result = number1 / number2;
                break;
        }
        account.setResult(result);
        return str;
    }

    @Override
    public boolean isCorrect(Account account, int myResult) {
        return account.getResult() == myResult;
    }

    public int random(){
        int b = random.nextInt(10);
        if (b == 0){
            return random();
        }else{
            return b;
        }
    }
}
