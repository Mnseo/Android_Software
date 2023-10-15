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
        // card 5�常 ����
        for (int i = 0; i < 5; i++) {
            Card c = new Card(suits[i % 4], ranks[i % 13]);
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
        // ù��° ī���� ���̰� ����
        int suit_value = cards.get(0).suit.ordinal() + 1;
        int rank_value = cards.get(0).rank.ordinal() + 1;
        System.out.println("ù��° ī���� ���̰�:" + suit_value + "/���ڰ�:" + rank_value);

    }


}
