package com.cibertec.examen2.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionRest {

    private static Retrofit retrofit = null;
    private static final String RUTA_API_REST ="http://env-4321929.j.layershift.co.uk/Examen2/rest/servicios/";

    //Permite Conectarnos al API Rest
    public static Retrofit getConnection() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(RUTA_API_REST).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
