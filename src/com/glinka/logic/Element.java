package com.glinka.logic;

public class Element {
    private int x;
    private int y;

    public Element(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "{" + x + ";" + y + "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Element e = (Element) o;
        return x == e.x && y == e.y;
    }

    @Override
    public int hashCode() {
        return x + y + 13;
    }
}
