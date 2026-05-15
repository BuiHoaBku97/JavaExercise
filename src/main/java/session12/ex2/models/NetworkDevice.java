package session12.ex2.models;

public class NetworkDevice extends Asset {
    private int numberOfPorts;
    private static final Double depreciationRate = 0.1;

    public NetworkDevice(String assetCode, String name, Double purchasePrice, int numberOfPorts) {
        super(assetCode, name, purchasePrice);
        this.numberOfPorts = numberOfPorts;
    }

    @Override
    public Double getMarketValue() {
        return getPurchasePrice() * (1 - depreciationRate);
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    @Override
    public String toString() {
        return super.toString() + "      Ports: " + numberOfPorts;
    }
}
