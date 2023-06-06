package br.com.example.pind.modal.estoque

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstoqueItem(

    /*
    verificar os nomes no BACK-END para inserir na string da TAG @SerializedName
     */

    @SerializedName("id")
    val productId: String?,
    @SerializedName("name")
    val productName: String?,
    @SerializedName("quantity")
    val quantity : String?,
    @SerializedName("")
    val manufacturingDate : String?,
    @SerializedName("")
    val expirationDate : String?,
    @SerializedName("unit_measurement")
    val measurementUnit : String?,
): Parcelable
