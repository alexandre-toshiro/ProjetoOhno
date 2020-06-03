package com.example.ohno11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ohno11.backend.Servico;

import java.util.ArrayList;

public class ServicoAdapter extends ArrayAdapter<Servico> {

    private final Context context;
    private final ArrayList<Servico> elementos;

    public ServicoAdapter(Context context, ArrayList<Servico> elementos){
        super(context, R.layout.rowservicos,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowservicos, parent, false);

        TextView outputNota = (TextView) rowView.findViewById(R.id.outputNota);
        TextView outputTempo = (TextView) rowView.findViewById(R.id.outputTempo);
        TextView outputValor = (TextView) rowView.findViewById(R.id.outputValor);
        TextView outputDecricao = (TextView) rowView.findViewById(R.id.outputDescricao);

        outputNota.setText("1");
        outputTempo.setText(""+elementos.get(position).getTempoDuracao());
        outputValor.setText(""+elementos.get(position).getValor());
        outputDecricao.setText(elementos.get(position).getDescricao());

        return rowView;
    }

}
