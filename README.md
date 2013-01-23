felica2money-android-lib
========================

FeliCa2Money for Android (以下FeliCa2Money)が読み込んだ
電子マネー明細を、他の Android アプリで受け取るための
ライブラリです。

FeliCa2Money は電子マネー明細を読み取り、他アプリに渡す機能
を持っています。これは startActivity を暗黙的 Intent で実行
することで行うようになっています。この Intent 内に明細情報
が入っていますので、これを読み取ることでデータを利用すること
が可能です。

使い方
======

src 以下のソースコードをプロジェクトに追加してください。

次に、Intent を受け取りたい Activity に intent-filter を記述します。
以下に例を示します。

        <activity
            android:name=".SampleActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="org.tmurakam.felica2money.FELICA_CARD_DATA" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            </activity>
        <activity

* action には "org.tmurakam.felica2money.FELICA_CARD_DATA" を指定してください。
* category には DEFAULT を指定してください。

Activity 側は、onCreate などで Intent を受取り、FelicaCard.getFromIntent()
を呼び出して Intent から FelicaCard クラスのインスタンスを抽出します。
本クラスのメソッドを呼び出すことで必要な情報が得られます。

以下に、取引一覧を表示するサンプルコードを示します。

     Intent intent = getIntent();
     if (intent.getAction().equals(FelicaCard.INTENT_ACTION)) {
         FelicaCard card = FelicaCard.getFromIntent(intent);
         for (FelicaTransaction transaction : card.getTransactions()) {
             Log.d(TAG, "日付: " + new Date(transaction.date).toString());
             Log.d(TAG, "説明: " + transaction.desc);
             Log.d(TAG, "金額: " + transaction.value);
             Log.d(TAG, "残高: " + transaction.balance);
         }
     }

ライセンス
==========

本ライブラリは Public Domain 扱いとします。
