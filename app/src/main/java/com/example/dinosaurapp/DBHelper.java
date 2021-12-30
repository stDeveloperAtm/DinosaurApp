package com.example.dinosaurapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "test";
    private static int DB_VERSION = 1;
    final String ID = "id";
    final String NAME ="name";
    final String IMAGE_NAME = "imageName";

    public DBHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void  onCreate(SQLiteDatabase db) {
        Log.d("TEST", "DBHelper onCreate");
        //テーブル作成用SQL文
        String sql = "CREATE TABLE Dinasaura(";
        sql += "id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql += "name text NOT NULL,";
        sql += "imageName TEXT NOT NULL);";

        //テーブル作成
        db.execSQL(sql);

        inputDefaultRecord(db);
    }


    private void inputDefaultRecord(SQLiteDatabase db) {
        Log.d("TEST", "DBHelper default insert start");

        inputRecord(db, "ティラノサウルス", "tyrannosaurus");


        //db.execSQL("insert into " + "Fruits" + " (" + "latitude" + "," + "longitude"  + "," + "name" + ")" + " values (33.587,130.422,'atomitech');");
        //db.execSQL("insert into " + "Fruits" + " (" + "name" + ")" + " values ('デフォルト2');");
        //db.execSQL("insert into " + "Fruits" + " (" + "name" + ")" + " values ('デフォルト3');");
        Log.d("TEST", "DBHelper default insert end");
    }

    public void inputRecord(SQLiteDatabase db, String inName, String inImageName) {
        db.execSQL("insert into " + "Position" + " (" + "latitude" + ", " + "longitude"  + ", " + "name" + ")" + " values (" + "'" + inName + "'" + ", " + "'" + inImageName + "'" + ");");
    }




/*
    public void insertRecord(Context context, TextView inputTextView, DBHelper helper){
        String fruitName;
        //テキストボックス内の文字を取得
        fruitName = inputTextView.getText().toString();
        Log.d("TEST", "end- register " + fruitName);
        //SQLiteDatabaseクラスの取得（書き込み用）
        SQLiteDatabase db = helper.getWritableDatabase();

        //テーブルに登録するレコードの設定準備
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", fruitName);

        //Fruitsテーブルに登録
        db.insert("Fruits", null, contentValue);

        //データベースをクローズ
        db.close();

        Toast toast = Toast.makeText(context, "登録したぜ",Toast.LENGTH_SHORT);
        toast.show();

        //inputTextViewをクリア
        inputTextView.setText("");
        Log.d("TEST", "end- register");

    }
*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
