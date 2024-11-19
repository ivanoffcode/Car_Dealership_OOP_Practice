package com.company.oop.dealership;

import com.company.oop.dealership.core.VehicleDealershipEngineImpl;

public class Startup {

    public static void main(String[] args) {
        VehicleDealershipEngineImpl engine = new VehicleDealershipEngineImpl();
        engine.start();
    }

}
