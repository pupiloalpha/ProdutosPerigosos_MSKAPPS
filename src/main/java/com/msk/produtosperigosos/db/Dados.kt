package com.msk.produtosperigosos.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produtos_perigosos")
data class Dados(
    // A ONU é a chave primária para garantir unicidade e busca rápida.
    @PrimaryKey
    val numeroONU: String,
    val descricao: String,
    val guiaRisco: String,
    val classeRisco: String,
    val numeroRisco: String
)