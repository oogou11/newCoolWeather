package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;

/**
 * Created by zhangxt on 2016/7/29.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

    /*创建省表*/
    public static final String CREATE_PROVINCE = "create table Province("
            + "id integer primary key autoincrement,"
            + "province_name text,"
            + "province_code text)";
    /*创建城市表*/
    public static final String CREATE_CITY = "create table City("
            + "id integer primary key autoincrement,"
            + "city_name text,"
            + "city_code text,"
            + "province_id integer)";

    /*创建county表*/
    public static final String CREATE_COUNTY = "create table County("
            + "id integer primary key autoincrement,"
            + "county_name text,"
            + "county_code text,"
            + "city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}