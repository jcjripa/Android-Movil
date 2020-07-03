package com.cibertec.examen2.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cibertec.examen2.R;
import com.cibertec.examen2.TipoReclamoActivity;
import com.cibertec.examen2.entidad.TipoReclamo;

import java.util.List;

public class TipoReclamoAdapter extends ArrayAdapter<TipoReclamo> {

    private Context context;
    private List<TipoReclamo> treclamos;

    public TipoReclamoAdapter(Context context, int resource, List<TipoReclamo> treclamos) {
        super(context, resource, treclamos);
        this.context = context;
        this.treclamos = treclamos;
    }

    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list, parent, false);


        TextView txtId = (TextView) rowView.findViewById(R.id.txtListViewID);
        TextView txtDescripcion = (TextView) rowView.findViewById(R.id.txtListViewName);

        txtId.setText(String.format("#ID: %d", treclamos.get(pos).getIdTipoReclamo()));
        txtDescripcion.setText(String.format("DESCRIPCION: %s", treclamos.get(pos).getDescripcion()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TipoReclamoActivity.class);
                intent.putExtra("var_id", String.valueOf(treclamos.get(pos).getIdTipoReclamo()));
                intent.putExtra("var_descripcion", treclamos.get(pos).getDescripcion());
                intent.putExtra("var_estado", treclamos.get(pos).getEstado());
                intent.putExtra("var_fecha", treclamos.get(pos).getFechaRegistro());
                intent.putExtra("var_metodo", "VER");
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
