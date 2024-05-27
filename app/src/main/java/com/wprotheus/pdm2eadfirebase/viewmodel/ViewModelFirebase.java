package com.wprotheus.pdm2eadfirebase.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wprotheus.pdm2eadfirebase.model.Disciplina;

import lombok.Data;

@Data
public class ViewModelFirebase extends ViewModel {
    private final MutableLiveData<Disciplina> dLiveData = new MutableLiveData<>();

    public MutableLiveData<Disciplina> getdLiveData() {
        return dLiveData;
    }
}