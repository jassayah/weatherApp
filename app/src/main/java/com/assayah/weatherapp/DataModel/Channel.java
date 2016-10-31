package com.assayah.weatherapp.DataModel;


public class Channel {

    private String title;
    private String link;
    private String language;
    private String description;
    private String lastBuildDate;
    private long ttl;
    private Location location;
    private Units units;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Image image;
    private Item item;


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public long getTtl() {
        return ttl;
    }

    public Location getLocation() {
        return location;
    }

    public Units getUnits() {
        return units;
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public Image getImage() {
        return image;
    }

    public Item getItem() {
        return item;
    }
}
