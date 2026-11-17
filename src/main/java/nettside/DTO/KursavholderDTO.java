package nettside.DTO;

public class KursavholderDTO {
    private int id;
    private String navn;
    private String adresse;
    private int addressId;

    public KursavholderDTO(int id, String navn, String adresse, int addressId) {
        this.id = id;
        this.navn = navn;
        this.adresse = adresse;
        this.addressId = addressId;
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getAddressId() {
        return addressId;
    }
}

