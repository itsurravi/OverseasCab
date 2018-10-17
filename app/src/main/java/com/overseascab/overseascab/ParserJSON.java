package com.overseascab.overseascab;

import com.overseascab.overseascab.Models.Vehicles;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserJSON {

    public static String id;
    public static String carname;
    public static String carimage;
    public static String basedistance;
    public static String baseprice;
    public static String unitdistance;
    public static String seatingcap;
    public static String booked;

    public static String ID = "id";
    public static String CARNAME = "carname";
    public static String CARIMG = "carimg";
    public static String BASEDISTANCE = "basedistance";
    public static String BASEPRICE = "baseprice";
    public static String UNITDISTANCE = "unitdistance";
    public static String SEATINGCAP = "seatingcap";
    public static String BOOKED = "booked";

    private JSONArray cars = null;

    private String json;

    public ParserJSON(String json) {
        this.json = json;
    }

    public List<Vehicles> parseJSON()
    {
        List<Vehicles> l = new ArrayList<>();
        JSONObject jsonObject = null;
        try
        {
            jsonObject = new JSONObject(json);
            cars = jsonObject.getJSONArray("result");

            for(int i=0;i<cars.length();i++)
            {
                JSONObject o = cars.getJSONObject(i);
                id = o.getString(ID);
                carname = o.getString(CARNAME);
                carimage = o.getString(CARIMG);
                basedistance = o.getString(BASEDISTANCE);
                baseprice = o.getString(BASEPRICE);
                unitdistance = o.getString(UNITDISTANCE);
                seatingcap = o.getString(SEATINGCAP);
                booked = o.getString(BOOKED);

                Vehicles v = new Vehicles(id, carname, carimage, basedistance, baseprice, unitdistance, seatingcap, booked);
                l.add(v);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return l;
    }

}
