package com.wprotheus.pdm2eadfirebase.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private static final DatabaseReference
            DISCIPLINA_REF = FirebaseDatabase.getInstance().getReference().child("Disciplina");

    private FirebaseUtils() {    }

    public static DatabaseReference getDisciplinaReference() {
        return DISCIPLINA_REF;
    }
}