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

        // �� ���� ���� ī�� ���� �����ϴ� �迭
        int[] suitCounts = new int[suits.length];

        // ���ϴ� ī�� ����ŭ ����
        int totalCardsToGenerate = 5; // ���ϴ� ī�� ��
        for (int i = 0; i < totalCardsToGenerate; i++) {
            Suit currentSuit = suits[i % 4];
            Rank currentRank = ranks[i % 13];

            // �ش� ������ ī�� �� ����
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
        // ù��° ī���� ���̰� ����
        int suit_value = cards.get(0).suit.ordinal() + 1;
        int rank_value = cards.get(0).rank.ordinal() + 1;
        System.out.println("ù��° ī���� ���̰�:" + suit_value + "/���ڰ�:" + rank_value);

        // �� ���̺��� ���� ī�� �� ���
        for (Suit suit : Suit.values()) {
            int remainingCards = totalCardsToGenerate / 4 - suitCounts[suit.ordinal()];
            System.out.println("���� " + suit + "�� ���� ī�� ��: " + remainingCards);
        }

    }


}
