package com.duksung.android_software;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Suit suits[] = Suit.values();
        Rank ranks[] = Rank.values();
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i=0; i<4; i++) {
            for(int j=0; j<13; j++) {
                Card c = new Card(suits[i], ranks[j]);
                c.flip();
                cards.add(c);
            }
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

    }


}
