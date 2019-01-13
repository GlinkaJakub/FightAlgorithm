package com.glinka;

import com.glinka.gamer.Gamer;
import com.glinka.util.Answer;

public class Main {

    public static void main(String[] args) {

        Gamer gamer = Gamer.provideInputConsumer();
        gamer.consumeConfig(new Answer.ConfigAnswer() {
            @Override
            public void notify(String answer) {
                System.out.println(answer);
            }
        });

        gamer.consumeBlackSpotsConfig(new Answer.BlackSpotsAnswer() {
            @Override
            public void notify(String answer) {
                System.out.println(answer);
            }
        });

        gamer.startGame(new Answer.MoveAnswer() {
            @Override
            public void notify(String answer) {
                System.out.println(answer);
            }
        });
    }
}
