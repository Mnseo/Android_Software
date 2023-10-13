package com.duksung.android_software;

enum Suit {spade, heart, diamond, club}

enum Rank {ace, two, three, four, five, six, seven,
            eight, nine, ten, jack, queen, king}

public class Card {
    Suit suit;
    Rank rank;
    boolean faceup;
    public Card(Suit s, Rank r) {
        suit = s; rank = r;
    }
    public void flip() {
        faceup = !faceup;
    }
    public String description() {
        if(!faceup) {
            return "BACK";
        } else {
            String tmp = null;
            switch(suit) {
                case spade: tmp = "spade/"; break;
                case heart: tmp = "heart/"; break;
                case diamond: tmp = "diamond/"; break;
                case club: tmp = "club/"; break;
                default: tmp = "wrong suit";
            }
            switch(rank) {
                case ace: tmp = tmp + "ace"; break;
                case jack: tmp = tmp + "jack"; break;
                case queen: tmp = tmp + "queen"; break;
                case king: tmp = tmp + "king"; break;
                default: tmp = tmp + (rank.ordinal()+1);
            }
            return tmp;
        }
    }
}