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

        /*좀 더 편리하게 처리하고, 원래의 함수를 크게 변형하지 않기 위해
        index[0-4]는 yourCards에 저장, index[5-9]는 myCards, 나머지는 remainCard에 저장했습니다 */
        for (int i =0; i< 4; i++) {
            for (int j = 0; j< 13; j++) {
                Card c = new Card(suits[i], ranks[j]);
                c.flip();
                cards.add(c);
            }
        }

        // 카드 셔플 (그대로 배치시 섞이지 않은 채로 배정되기 때문에 셔플 후 배정했습니다.
        Collections.shuffle(cards);

        // 카드 분배
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (i < 5) { yourCards.add(currentCard); }
            else if (i < 10) { myCards.add(currentCard); }
            else { remainCards.add(currentCard); }
        }

        // 첫번째 카드의 무늬값 숫자
        int suit_value = cards.get(0).suit.ordinal() + 1;
        int rank_value = cards.get(0).rank.ordinal() + 1;
        System.out.println("첫번째 카드의 무늬값:" + suit_value + "/숫자값:" + rank_value);


        // yourResult TextView에 1번째 플레이어 (your) 카드 정보 설정
        setCardInfoToTv(yourResult, yourCards, "Your Result");
        // myResult TextView에 2번째 플레이어 (my) 카드 정보 설정
        setCardInfoToTv(myResult, myCards, "My Result");


        // remainCardList에서 각 무늬별로 남은 카드 수 출력
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

    //second -> main으로 돌아올때 다른 카드값으로 새로고침 하기 위함
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("restart", "restart");

        // cards 리스트를 초기화 (중첩되어 Score에 들어가는걸 방지하기 위해)
        yourCards.clear();
        myCards.clear();
        remainCards.clear();

        //card는 초기화하면 모든 값이 0으로 뜨기 때문에 shuffle만 해줍니다
        Collections.shuffle(cards);

        // 카드 분배
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            if (i < 5) { yourCards.add(currentCard); }
            else if (i < 10) { myCards.add(currentCard); }
            else { remainCards.add(currentCard); }
        }

        // yourResult TextView에 1번째 플레이어 (your) 카드 정보 설정
        setCardInfoToTv(yourResult, yourCards, "Your Result");
        // myResult TextView에 2번째 플레이어 (my) 카드 정보 설정
        setCardInfoToTv(myResult, myCards, "My Result");

        // remainCardList에서 각 무늬별로 남은 카드 수 출력
        String remainText = remainResult.getText().toString() + "\n"; //기존 결과
        for (Suit suit : Suit.values()) {
            int remainingCount = (int) remainCards.stream()
                    .filter(c -> c.suit == suit)
                    .count();
            remainText += suit + ": " + remainingCount + "\n";
        }

        remainResult.setText(remainText);

    }

    // 카드 점수 계산 함수
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

    // 카드 정보를 TextView에 설정하는 함수
    private static void setCardInfoToTv(TextView textView, ArrayList<Card> cardList, String label) {
        String resultText = label + "\n";
        for (Card c : cardList) {
            resultText += c.description() + "\n";
        }
        textView.setText(resultText);
    }

}
