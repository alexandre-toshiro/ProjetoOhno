package com.example.ohno11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ohno11.backend.Veiculo;

import java.util.ArrayList;

class VeiculoAdapter extends ArrayAdapter<Veiculo> {

    private final Context context;
    private final ArrayList<Veiculo> elementos;

    public VeiculoAdapter(Context context, ArrayList<Veiculo> elementos)
    {
        super(context, R.layout.rowveiculo,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowveiculo, parent, false);
        TextView outputMarca = (TextView) rowView.findViewById(R.id.outputMarca);
        TextView outputModelo = (TextView) rowView.findViewById(R.id.outputModelo);
        TextView outputPlaca = (TextView) rowView.findViewById(R.id.outputPlaca);

        outputMarca.setText(elementos.get(position).getMarca());
        outputModelo.setText(elementos.get(position).getModelo());
        outputPlaca.setText(elementos.get(position).getPlaca());
        return rowView;
    }

}

