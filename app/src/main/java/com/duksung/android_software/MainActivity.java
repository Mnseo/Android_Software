package com.duksung.android_software;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Suit suits[] = Suit.values();
        Rank ranks[] = Rank.values();
        ArrayList<Card> cards = new ArrayList<Card>();

        // 각 무늬 별로 카드 수를 저장하는 배열
        int[] suitCounts = new int[suits.length];

        // 원하는 카드 수만큼 생성
        int totalCardsToGenerate = 5; // 원하는 카드 수
        for (int i = 0; i < totalCardsToGenerate; i++) {
            Suit currentSuit = suits[i % 4];
            Rank currentRank = ranks[i % 13];

            // 해당 무늬의 카드 수 증가
            suitCounts[currentSuit.ordinal()]++;

            Card c = new Card(currentSuit, currentRank);
            c.flip();
            cards.add(c);
        }

        for(Card c : cards) {
            System.out.println(c.description());
        }
        System.out.println("==========================");
        Collections.shuffle(cards);
        for(Card c : cards) {
            System.out.println(c.description());
        }
        // 첫번째 카드의 무늬값 숫자
        int suit_value = cards.get(0).suit.ordinal() + 1;
        int rank_value = cards.get(0).rank.ordinal() + 1;
        System.out.println("첫번째 카드의 무늬값:" + suit_value + "/숫자값:" + rank_value);

        // 각 무늬별로 남은 카드 수 출력
        for (Suit suit : Suit.values()) {
            int remainingCards = totalCardsToGenerate / 4 - suitCounts[suit.ordinal()];
            System.out.println("무늬 " + suit + "의 남은 카드 수: " + remainingCards);
        }

    }


}
