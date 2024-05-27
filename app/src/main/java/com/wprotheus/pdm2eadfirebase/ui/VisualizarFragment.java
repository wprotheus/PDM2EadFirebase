package com.wprotheus.pdm2eadfirebase.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wprotheus.pdm2eadfirebase.databinding.FragmentVisualizarBinding;
import com.wprotheus.pdm2eadfirebase.model.Disciplina;
import com.wprotheus.pdm2eadfirebase.util.DisciplinaAdapter;
import com.wprotheus.pdm2eadfirebase.util.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;

public class VisualizarFragment extends Fragment {
    private RecyclerView rvDisciplinas;
    private DisciplinaAdapter dAdapter;
    private List<Disciplina> disciplinaList;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentVisualizarBinding binding = FragmentVisualizarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        configurandoRecyclerView(binding);
        preparandoDadosVisualizacao();
        return root;
    }

    private void configurandoRecyclerView(FragmentVisualizarBinding binding) {
        rvDisciplinas = binding.rvDisciplina;
        rvDisciplinas.setLayoutManager(new LinearLayoutManager(getContext()));
        disciplinaList = new ArrayList<>();
        dAdapter = new DisciplinaAdapter(disciplinaList);
        rvDisciplinas.setAdapter(dAdapter);
    }

    private void preparandoDadosVisualizacao() {
        reference = FirebaseUtils.getDisciplinaReference();
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                disciplinaList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Disciplina disciplina = snapshot.getValue(Disciplina.class);
                    if (disciplina != null)
                        disciplinaList.add(disciplina);
                }
                dAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}