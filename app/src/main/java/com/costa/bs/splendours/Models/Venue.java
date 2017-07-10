package com.costa.bs.splendours.Models;

import java.util.ArrayList;

/**
 * Created by binusadanand on 09/07/2017.
 */

public class Venue {
    public String id;
    public String name;
    public FsContact contact;
    public FsLocation location;
    public ArrayList<FsCategory> categories;
    public boolean verified;
    public Stats stats;
    public String url;

    public class FsContact {
        public String twitter;
        public String instagram;
        public String facebook;
        public String facebookUsername;
        public String FacebookName;

    }

    public class FsLocation {
        public String address;
        public String crossStreet;
        public double lat;
        public double lng;
        public int distance;
        public String postalCode;
        public String cc;
        public String city;
        public String state;
        public String country;
        public ArrayList<String> formattedAddress;
    }

    public class FsCategory {

    }

    public class Stats {

    }
}
