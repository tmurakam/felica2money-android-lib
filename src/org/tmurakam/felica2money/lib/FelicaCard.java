package org.tmurakam.felica2money.lib;

import android.content.Intent;

import java.util.ArrayList;

/**
 * FeliCa カード
 */
public class FelicaCard {
    /**
     * FeliCa2Money が送信する Intent Action名。
     * category は DEFAULT。
     */
    public final static String INTENT_ACTION = "org.tmurakam.felica2money.FELICA_CARD_DATA";
    
    /**
     * カード名称 (Suica, Edy など)
     */
    protected String mName;
    
    /**
     * カードを識別するID。 カード種類によって種類が異なる。
     */
    protected String mCardId;
    
    /**
     * 取引一覧
     */
    protected ArrayList<FelicaTransaction> mTransactions;
    
    protected final static String CARD_NAME = "card_name";
    protected final static String CARD_IDENTIFIER = "card_identifier";
    protected final static String CARD_TRANSACTIONS = "card_transactions";
    
    public FelicaCard() {}
    
    /**
     * カード名を返す
     * @return
     */
    public String getName() {
        return mName;
    }
    
    /**
     * カードIDを返す
     * @return
     */
    public String getCardId() {
        return mCardId;
    }

    /**
     * 取引リストを返す
     * @return
     */
    public ArrayList<FelicaTransaction> getTransactions() {
        return mTransactions;
    }
    
    /**
     * Intent から FelicaCard を取り出す
     * 
     * @param bundle
     * @return
     */
    public static FelicaCard getFromIntent(Intent intent) {
        return new FelicaCard(intent);
    }
    
    protected FelicaCard(Intent intent) {
        mName = intent.getStringExtra(CARD_NAME);
        mCardId = intent.getStringExtra(CARD_IDENTIFIER);
        mTransactions = intent.getParcelableArrayListExtra(CARD_TRANSACTIONS);
    }
}
