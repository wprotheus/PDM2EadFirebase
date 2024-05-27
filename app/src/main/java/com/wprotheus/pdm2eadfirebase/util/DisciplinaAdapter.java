package com.wprotheus.pdm2eadfirebase.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wprotheus.pdm2eadfirebase.R;
import com.wprotheus.pdm2eadfirebase.databinding.DisciplinasLayoutBinding;
import com.wprotheus.pdm2eadfirebase.model.Disciplina;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.MyViewHolder> {
    private List<Disciplina> disciplinaList;

    public DisciplinaAdapter(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DisciplinasLayoutBinding binding = DisciplinasLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Disciplina disciplina = disciplinaList.get(position);
        holder.bind(disciplina);
    }

    @Override
    public int getItemCount() {
        return disciplinaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final DisciplinasLayoutBinding binding;

        public MyViewHolder(DisciplinasLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Disciplina disciplina) {
            binding.ivIlustracao.setImageResource(R.drawable.disciplinas);
            binding.tvSubjectName.setText(disciplina.getNome());
            binding.tvSubjectGrade.setText(disciplina.getNota());
        }
    }
}