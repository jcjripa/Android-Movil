package com.cibertec.examen2;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.cibertec.examen2.entidad.TipoReclamo;
        import com.cibertec.examen2.servicio.ServicioRest;
        import com.cibertec.examen2.util.ConnectionRest;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class TipoReclamoActivity extends AppCompatActivity {

    ServicioRest servicio;
    EditText edtUId, edtDescripcion,edtFecha;
    Spinner spnEstado;
    Button btnSave;
    Button btnDel;
    TextView txtUId;
    final String metodo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiporeclamo);

        setTitle("CRUD de Tipo Reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = (TextView) findViewById(R.id.txtIdTipoReclamo);
        edtUId = (EditText) findViewById(R.id.edtIdTipoReclamo);
        edtDescripcion = (EditText) findViewById(R.id.edtTipoReclamoDescripcion);
        edtFecha = (EditText) findViewById(R.id.edtTipoReclamoFecha);
        spnEstado = (Spinner) findViewById(R.id.spnTipoReclamoEstado);
        btnSave = (Button) findViewById(R.id.btnTipoReclamoSave);
        btnDel = (Button) findViewById(R.id.btnTipoReclamoDel);

        servicio = ConnectionRest.getConnection().create(ServicioRest.class);
        Bundle extras = getIntent().getExtras();
        final String metodo = extras.getString("var_metodo");
        final String var_id = extras.getString("var_id");

        if (metodo.equals("VER")) {
            String var_descripcion = extras.getString("var_descripcion");
            String var_estado = extras.getString("var_estado");
            String var_fecha = extras.getString("var_fecha");

            edtUId.setText(var_id);
            edtDescripcion.setText(var_descripcion);
            selectValue(spnEstado, var_estado);
            edtFecha.setText(var_fecha);
            edtUId.setFocusable(false);
        }else if (metodo.equals("REGISTRAR")) {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TipoReclamo u = new TipoReclamo();
                u.setDescripcion(edtDescripcion.getText().toString());
                u.setEstado(spnEstado.getSelectedItem().toString());
                u.setFechaRegistro(edtFecha.getText().toString());
                if (metodo.equals("VER")) {
                    u.setIdTipoReclamo(Integer.parseInt(var_id));
                    mensaje("Se pulsó  actualizar");
                    update(u);
                } else if (metodo.equals("REGISTRAR")) {
                    mensaje("Se pulsó agregar");
                    add(u);
                }

                Intent intent = new Intent(TipoReclamoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje("Se pulsó eliminar");
                delete(Integer.parseInt(var_id));
                Intent intent = new Intent(TipoReclamoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add(TipoReclamo u) {
        Call<TipoReclamo> call = servicio.agregaTipoReclamo(u);
        call.enqueue(new Callback<TipoReclamo>() {
            @Override
            public void onResponse(Call<TipoReclamo> call, Response<TipoReclamo> response) {
                if (response.isSuccessful()) {
                    mensaje("Registro exitoso");
                }
            }
            @Override
            public void onFailure(Call<TipoReclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void update(TipoReclamo u) {
        Call<TipoReclamo> call = servicio.actualizaTipoReclamo(u);
        call.enqueue(new Callback<TipoReclamo>() {
            @Override
            public void onResponse(Call<TipoReclamo> call, Response<TipoReclamo> response) {
                if (response.isSuccessful()) {
                    mensaje("Actualización exitosa");
                }
            }
            @Override
            public void onFailure(Call<TipoReclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void delete(int id) {
        Call<TipoReclamo> call = servicio.eliminaTipoReclamo(id);
        call.enqueue(new Callback<TipoReclamo>() {
            @Override
            public void onResponse(Call<TipoReclamo> call, Response<TipoReclamo> response) {
                if (response.isSuccessful()) {
                    mensaje("Eliminación exitosa");
                }
            }
            @Override
            public void onFailure(Call<TipoReclamo> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void mensaje(String msg) {
        Toast toast1 = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast1.show();
    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }

}
