package com.wprotheus.pdm2eadfirebase.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseApp;
import com.wprotheus.pdm2eadfirebase.R;
import com.wprotheus.pdm2eadfirebase.databinding.ActivityMainBinding;
import com.wprotheus.pdm2eadfirebase.ui.CadastroFragment;
import com.wprotheus.pdm2eadfirebase.ui.HomeFragment;
import com.wprotheus.pdm2eadfirebase.ui.VisualizarFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private HomeFragment hFragment;
    private CadastroFragment cFragment;
    private VisualizarFragment vFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        hFragment = new HomeFragment();
        cFragment = new CadastroFragment();
        vFragment = new VisualizarFragment();
        listenerFragmentos();
        alternandoFragmentos(hFragment);
    }

    private void listenerFragmentos() {
        mBinding.btnHome.setOnClickListener(v -> alternandoFragmentos(hFragment));
        mBinding.btnCadastro.setOnClickListener(v -> alternandoFragmentos(cFragment));
        mBinding.btnVisualizar.setOnClickListener(v -> alternandoFragmentos(vFragment));
    }

    private void alternandoFragmentos(Fragment destino) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragmentos, destino).commit();
    }
}