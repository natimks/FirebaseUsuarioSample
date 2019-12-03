package br.ifsc.edu.firebaseauladdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    Context mContext;
    int mResource;
    ArrayList<Pessoa> mDataSet;

    public PessoaAdapter(Context mContext, int mResource, ArrayList<Pessoa> mDataSet) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(mResource, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa p = mDataSet.get(position);
        holder.tvNome.setText(p.getNome());
        holder.tvSexo.setText(p.getSexo());
        holder.tvCpf.setText(p.getCpf());
        holder.tvId.setText(p.id);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvNome, tvSexo, tvCpf;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id_tv);
            tvNome = itemView.findViewById(R.id.nome_tv);
            tvSexo = itemView.findViewById(R.id.sexo_tv);
            tvCpf = itemView.findViewById(R.id.cpf_tv);

        }
    }
}
