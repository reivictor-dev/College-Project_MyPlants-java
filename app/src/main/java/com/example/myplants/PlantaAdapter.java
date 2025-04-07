package com.example.myplants;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myplants.database.PlantaDAO;
import com.example.myplants.model.Planta;

import java.util.List;

public class PlantaAdapter extends BaseAdapter {

    private Context context;
    private List<Planta> plantas;
    private PlantaDAO plantaDAO;

    public PlantaAdapter(Context context, List<Planta> plantas) {
        this.context = context;
        this.plantas = plantas;
        this.plantaDAO = new PlantaDAO(context);
    }

    @Override
    public int getCount() {
        return plantas.size();
    }

    @Override
    public Object getItem(int position) {
        return plantas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlantaViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_planta, parent, false);
            holder = new PlantaViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PlantaViewHolder) convertView.getTag();
        }

        Planta planta = plantas.get(position);
        holder.nomeTextView.setText(planta.getNome());
        holder.especieTextView.setText(planta.getEspecie());
        holder.quantidadeTextView.setText(String.valueOf(planta.getQuantidade()));
        holder.alturaTextView.setText(String.valueOf(planta.getAltura()) + " cm");
        holder.toxicaCheckBox.setChecked(planta.isToxica());

        // Ao clicar no botão de editar, passamos o ID correto da planta para a intent
        holder.editarButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlantaDetailActivity.class);
            intent.putExtra("plantaId", planta.getId());  // Corrigido para "plantaId"
            context.startActivity(intent);
        });

        holder.excluirButton.setOnClickListener(v -> {
            plantaDAO.delete(planta.getId());
            plantas.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }

    public static class PlantaViewHolder {
        TextView nomeTextView, especieTextView, quantidadeTextView, alturaTextView;
        CheckBox toxicaCheckBox;
        Button editarButton, excluirButton;

        public PlantaViewHolder(View itemView) {
            nomeTextView = itemView.findViewById(R.id.text_nome);
            especieTextView = itemView.findViewById(R.id.text_especie);
            quantidadeTextView = itemView.findViewById(R.id.text_quantidade);
            alturaTextView = itemView.findViewById(R.id.text_altura);
            toxicaCheckBox = itemView.findViewById(R.id.check_toxica);
            editarButton = itemView.findViewById(R.id.btn_editar);
            excluirButton = itemView.findViewById(R.id.btn_excluir);
        }
    }
}
