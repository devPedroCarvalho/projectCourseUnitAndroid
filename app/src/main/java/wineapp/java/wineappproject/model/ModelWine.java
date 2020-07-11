package wineapp.java.wineappproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelWine {
    @SerializedName("pairedWines")
    private List<String>pairedWines;

    @SerializedName("pairingText")
    private String pairingText;


    public ModelWine(List pairedWines, String pairingText) {
        this.pairedWines = pairedWines;
        this.pairingText = pairingText;
    }
    public List<String> getPairedWines() {
        return pairedWines;
    }

    public String getPairingText(){
        return pairingText;
    }

}
