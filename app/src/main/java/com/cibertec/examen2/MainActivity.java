package com.cibertec.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cibertec.examen2.adaptador.TipoReclamoAdapter;
import com.cibertec.examen2.entidad.TipoReclamo;
import com.cibertec.examen2.servicio.ServicioRest;
import com.cibertec.examen2.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnLista;
    ListView listView;
    TipoReclamoAdapter adaptadorListView;
    ServicioRest servicio;
    List<TipoReclamo> list = new ArrayList<TipoReclamo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Retrofit 2 CRUD Demo");

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnLista = (Button) findViewById(R.id.btnLista);

        // Al adaptador se le pasa la data y el diseño
        listView = (ListView) findViewById(R.id.listView);

        // Se crea la conexion al servicio
        servicio = ConnectionRest.getConnection().create(ServicioRest.class);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el listado");
                listData();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó el agregar");
                Intent intent = new Intent(MainActivity.this, TipoReclamoActivity.class);
                intent.putExtra("var_metodo", "REGISTRAR");
                startActivity(intent);
            }
        });

        //Para cargar los datos al inicio
        listData();
    }


    public void listData(){
        Call<List<TipoReclamo>> call = servicio.listaTipoReclamo();
        call.enqueue(new Callback<List<TipoReclamo>>() {
            @Override
            public void onResponse(Call<List<TipoReclamo>> call, Response<List<TipoReclamo>> response) {
                if(response.isSuccessful()){
                    //Aqui es donde obtiene la data y se coloca en el list
                    mensaje("Listado exitoso");
                    list = response.body();
                    adaptadorListView = new TipoReclamoAdapter(MainActivity.this, R.layout.activity_list, list);
                    listView.setAdapter(adaptadorListView);
                }
            }

            @Override
            public void onFailure(Call<List<TipoReclamo>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}
