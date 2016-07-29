package com.leyouv.newcoolweather.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leyouv.newcoolweather.model.City;
import com.leyouv.newcoolweather.model.County;
import com.leyouv.newcoolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxt on 2016/7/29.
 */
public class CoolWeatherDB {
    /*数据库名称*/
    public static final String DB_NAME = "cool_weather";

    /*数据库版本*/
    public static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB != null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    /*数据库中添加记录*/
    public void saveProvince(Province province) {
        if (province != null) {
            db.execSQL("insert into Province(province_name,province_code " +
                            "values(?,?)",
                    new String[]{province.getProvinceName(), province.getProvinceCode()});
        }
    }

    /*读取所有的Province*/
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.rawQuery("select * from Province", null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /*插入城市记录*/
    public void saveCity(City city) {
        if (city != null) {
            db.execSQL("insert into City(city_name,city_code,province_id)values(?,?,?)",
                    new String[]{city.getCityName(), city.getCityCode(),
                            Integer.toString(city.getProvinceId())});
        }
    }

    /*获取城市记录*/
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.rawQuery("select * from City where province_id=?", new String[]{
                Integer.toString(provinceId)
        });
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return  list;
    }

    /*插入county数据*/
    public  void saveCounty(County county){
        if(county!=null){
            db.execSQL("insert into County(count_name,county_code,city_id" +
                            "values(?,?,?)",
                    new String[]{county.getCountyName(),county.getCountyCode(),
                            Integer.toString(county.getCityId())} );
        }
    }

    /*获取county数据*/
    public List<County> loadCounty(int cityId){
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.rawQuery("select * from county where city_id=?",new String[]{
                Integer.toString(cityId)
        });
        if(cursor.moveToFirst()){
            do {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("count_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("count_code")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
}
