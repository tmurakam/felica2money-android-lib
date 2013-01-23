package org.tmurakam.felica2money.lib;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * FeliCa の１明細を表すクラス
 */
public class FelicaTransaction implements Parcelable {
    /*
     * 以下は取引の種別を表す定数。OFX出力時に使用するが、FeliCa の場合取引にこれらの
     * 属性は入っていないので、あまり意味がない情報。
     */
    /** 利息 */
    public static final int INT = 0;
    /** 配当 */
    public static final int DIV = 1;
    /** 振り込み入金、取り立て入金、自動引き落とし戻し入金 */
    public static final int DIRECT_DEP = 2;
    /** その他入金 */
    public static final int DEP = 3;
    
    /** 支払い */
    public static final int PAYMENT = 4;
    /** 現金支払　*/
    public static final int CASH = 5;
    /** ATM支払 */
    public static final int ATM = 6;
    /** 小切手支払 */
    public static final int CHECK = 7;
    /** その他出金 */
    public static final int DEBIT = 8;
    
    /**
     * 取引連番 (カードによっては存在しない(0))
     */
    public int id;
    
    /**
     * トランザクションID
     * トランザクションを識別するためのキー。FeliCa2Money が自動生成する。
     */
    public String transaction_id;
    
    /**
     * 日付
     */
    public Date date;
    /**
     * 取引種別 
     */
    public int type;
    /**
     * 説明 
     */
    public String desc;
    /**
     * メモ
     */
    public String memo;
    /**
     * 金額
     */
    public int value;
    /**
     * 残高
     */
    public int balance;
    
    public FelicaTransaction dup() {
        FelicaTransaction t = new FelicaTransaction();
        t.id = this.id;
        t.transaction_id = this.transaction_id;
        t.date = this.date;
        t.type = this.type;
        t.desc = this.desc;
        t.memo = this.memo;
        t.value = this.value;
        t.balance = this.balance;
        return t;
    }
    
    public FelicaTransaction() {
    }

    /*
     * 以下は  Parcelable 対応
     */
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.transaction_id);
        dest.writeInt(this.type);
        dest.writeLong(this.date.getTime());
        dest.writeInt(this.value);
        dest.writeInt(this.balance);
        dest.writeString(this.desc);
        dest.writeString(this.memo);
    }
    
    protected FelicaTransaction(Parcel in) {
        this.id = in.readInt();
        this.transaction_id = in.readString();
        this.type = in.readInt();
        this.date = new Date(in.readLong());
        this.value = in.readInt();
        this.balance = in.readInt();
        this.desc = in.readString();
        this.memo = in.readString();
    }
    
    public static final Parcelable.Creator<FelicaTransaction> CREATOR = new Parcelable.Creator<FelicaTransaction> () {
        @Override
        public FelicaTransaction createFromParcel(Parcel source) {
            return new FelicaTransaction(source);
        }

        @Override
        public FelicaTransaction[] newArray(int size) {
            return new FelicaTransaction[size];
        }
    };
}
