package com.duksung.android_software;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView yourResult, myResult, remainResult;
    int yourScore, myScore;
    private ArrayList<Card> myCards = new ArrayList<Card>();
    private ArrayList<Card> yourCards = new ArrayList<Card>();
    private ArrayList<Card> remainCards = new ArrayList<Card>();
    private ArrayList<Card> cards = new ArrayList<Card>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        yourResult = (TextView) findViewById(R.id.your_result_tv);
        myResult = (TextView) findViewById(R.id.mine_result_tv);
        remainResult = (TextView) findViewById(R.id.suit_remain_tv);

        Suit suits[] = Suit.values();
        Rank ranks[] = Rank.values();

        /*�� �� ���ϰ� ó���ϰ�, ������ �Լ��� ũ�� �������� �ʱ� ����
        index[0-4]�� yourCards�� ����, index[5-9]�� myCards, �������� remainCard�� �����߽��ϴ� */
        for (int i =0; i< 4; i++) {
            for (int j = 0; j< 13; j++) {
                Card c = new Card(suits[i], ranks[j]);
                c.flip();
                cards.add(c);
            }
        }

        // ī�� ���� (�״�� ��ġ�� ������ ���� ä�� �����Ǳ� ������ ���� �� �����߽��ϴ�.
        Collections.shuffle(cards);

        // ī�� �й�
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (i < 5) { yourCards.add(currentCard); }
            else if (i < 10) { myCards.add(currentCard); }
            else { remainCards.add(currentCard); }
        }

        // ù��° ī���� ���̰� ����
        int suit_value = cards.get(0).suit.ordinal() + 1;
        int rank_value = cards.get(0).rank.ordinal() + 1;
        System.out.println("ù��° ī���� ���̰�:" + suit_value + "/���ڰ�:" + rank_value);


        // yourResult TextView�� 1��° �÷��̾� (your) ī�� ���� ����
        setCardInfoToTv(yourResult, yourCards, "Your Result");
        // myResult TextView�� 2��° �÷��̾� (my) ī�� ���� ����
        setCardInfoToTv(myResult, myCards, "My Result");


        // remainCardList���� �� ���̺��� ���� ī�� �� ���
        String remainText = "Remaining Cards:\n";
        for (Suit suit : Suit.values()) {
            int remainingCount = (int) remainCards.stream()
                    .filter(c -> c.suit == suit)
                    .count();
            remainText += suit + ": " + remainingCount + "\n";
        }

        remainResult.setText(remainText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourScore = calculateScore(yourCards);
                myScore = calculateScore(myCards);
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("yourScore", yourScore);
                intent.putExtra("myScore", myScore);
                startActivity(intent);
            }
        });
    }

    //second -> main���� ���ƿö� �ٸ� ī�尪���� ���ΰ�ħ �ϱ� ����
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("restart", "restart");

        // cards ����Ʈ�� �ʱ�ȭ (��ø�Ǿ� Score�� ���°� �����ϱ� ����)
        yourCards.clear();
        myCards.clear();
        remainCards.clear();

        //card�� �ʱ�ȭ�ϸ� ��� ���� 0���� �߱� ������ shuffle�� ���ݴϴ�
        Collections.shuffle(cards);

        // ī�� �й�
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (i < 5) { yourCards.add(currentCard); }
            else if (i < 10) { myCards.add(currentCard); }
            else { remainCards.add(currentCard); }
        }

        // yourResult TextView�� 1��° �÷��̾� (your) ī�� ���� ����
        setCardInfoToTv(yourResult, yourCards, "Your Result");
        // myResult TextView�� 2��° �÷��̾� (my) ī�� ���� ����
        setCardInfoToTv(myResult, myCards, "My Result");

        // remainCardList���� �� ���̺��� ���� ī�� �� ���
        String remainText = remainResult.getText().toString() + "\n"; //���� ���
        for (Suit suit : Suit.values()) {
            int remainingCount = (int) remainCards.stream()
                    .filter(c -> c.suit == suit)
                    .count();
            remainText += suit + ": " + remainingCount + "\n";
        }

        remainResult.setText(remainText);

    }

    // ī�� ���� ��� �Լ�
    private static int calculateScore(ArrayList<Card> cardList) {
        int totalScore = 0;
        for (Card c : cardList) {
            int suitIndex = c.suit.ordinal();
            int rankIndex = c.rank.ordinal();
            int cardScore = (suitIndex + 1) * (rankIndex + 1);
            totalScore += cardScore;
        }
        return totalScore;
    }

    // ī�� ������ TextView�� �����ϴ� �Լ�
    private static void setCardInfoToTv(TextView textView, ArrayList<Card> cardList, String label) {
        String resultText = label + "\n";
        for (Card c : cardList) {
            resultText += c.description() + "\n";
        }
        textView.setText(resultText);
    }

}
