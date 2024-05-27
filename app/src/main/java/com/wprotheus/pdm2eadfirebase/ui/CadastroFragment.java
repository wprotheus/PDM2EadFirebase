package com.wprotheus.pdm2eadfirebase.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.wprotheus.pdm2eadfirebase.R;
import com.wprotheus.pdm2eadfirebase.databinding.FragmentCadastroBinding;
import com.wprotheus.pdm2eadfirebase.model.Disciplina;
import com.wprotheus.pdm2eadfirebase.util.FirebaseUtils;

public class CadastroFragment extends Fragment {
    private FragmentCadastroBinding fcBinding;
    private DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseUtils.getDisciplinaReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fcBinding = FragmentCadastroBinding.inflate(inflater, container, false);
        View root = fcBinding.getRoot();
        listenerCadastro();
        return root;
    }

    private void listenerCadastro() {
        fcBinding.btnCadastrar.setOnClickListener(v -> cadastrarDisciplina());
        listenerFocus();
    }

    private void cadastrarDisciplina() {
        String nome = fcBinding.tietNome.getText().toString();
        String nota = fcBinding.tietNota.getText().toString();

        if (nome.isEmpty() || nota.isEmpty()) {
            Toast.makeText(requireContext(), R.string.msg_erro, Toast.LENGTH_SHORT).show();
            return;
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setNota(nota);

        reference.push().setValue(disciplina, (databaseError, databaseReference) -> {
            if (databaseError == null)
                Toast.makeText(requireContext(), R.string.msg_certo, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(requireContext(), R.string.msg_erro, Toast.LENGTH_SHORT).show();
            limparValores();
        });
    }

    private void listenerFocus() {
        fcBinding.tietNome.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) ocultarTeclado(v);
        });
        fcBinding.tietNota.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) ocultarTeclado(v);
        });
    }

    private void ocultarTeclado(View view) {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void limparValores() {
        fcBinding.tietNome.setText("");
        fcBinding.tietNome.requestFocus();
        fcBinding.tietNota.setText("");
    }
}