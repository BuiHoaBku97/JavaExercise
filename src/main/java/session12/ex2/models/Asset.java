package session12.ex2.models;

public abstract class Asset {
    private String assetCode;
    private String name;
    private Double purchasePrice;

    public Asset(String assetCode, String name, Double purchasePrice) {
        this.assetCode = assetCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    public abstract Double getMarketValue();

    public static void showValue(Asset asset) {
        System.out.println("Current market value: " + asset.getMarketValue());
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Asset code: " + assetCode + "      Name: " + name;
    }
}
