package com.glinka.util;

import com.glinka.logic.Spot;

import java.util.ArrayList;

public class Parser {

    public static int size;

    public static ArrayList<Spot> parseInputData(String blackElementDataConfiguration){
        ArrayList<Spot> spots = new ArrayList<Spot>();
        String[] points = blackElementDataConfiguration.split(",");
        for (String point : points) {
            String[] xy = point.split(";");
            String x = xy[0].substring(1, xy[0].length());
            String y = xy[1].substring(0, xy[1].length()-1);
            spots.add(new Spot(Integer.valueOf(x), Integer.valueOf(y)));
        }
        return spots;
    }

    public static String parseOutputData(ArrayList<Spot> spots){
        StringBuilder stringBuilder = new StringBuilder();
        for( Spot spot : spots){
            stringBuilder.append("{");
            stringBuilder.append(spot.getX());
            stringBuilder.append(",");
            stringBuilder.append(spot.getY());
            stringBuilder.append("}");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static String parseOutputData(Spot spot){
        return "{" +
                (spot.getX()) / size +
                ";" +
                (spot.getX() - ((spot.getX() / size) * size)) +
                "},{" +
                (spot.getY()) / size +
                ";" +
                (spot.getY() - ((spot.getY() / size) * size)) +
                "}";
    }

    public static Spot parseInputDataToElement(String data){
        return getCords(parseInputData(data));
    }

    public static Spot parseInputDataToElement(ArrayList<Spot> spots){
        return getCords(spots);
    }

    private static Spot getCords(ArrayList<Spot> spots){
        return new Spot(spots.get(0).getX() * size + spots.get(0).getY(), spots.get(1).getX() * size + spots.get(1).getY());
    }
}