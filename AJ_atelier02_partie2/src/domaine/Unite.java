package domaine;

public enum Unite {
    //Declaration des valeurs
    GRAMME("gr"), KILOGRAMME("kg"), LITRE("l"), MILLILITRE("ml"), CENTILITRE("cl"), DECILITRE("dl"),
    CUILLER_A_CAFE("cc"), CUILLER_A_THE("ct"), CUILLER_A_DESSERT("cd"), CUILLER_A_SOUPE("cs"), PINCEE("pincée"), UN_PEU("peu"),NEANT("");

    private String abreviation;
    Unite(String abreviation){
        this.abreviation = abreviation;
    }

    public String getAbreviation() {
        return abreviation;
    }

    @Override
    public String toString() {
        return getAbreviation();
    }
}
