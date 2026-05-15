package session12.ex2.models;

public class Computer extends Asset {
    private String ram;
    private String cpu;
    private static final Double depreciationRate = 0.2;

    public Computer(String assetCode, String name, Double purchasePrice, String ram, String cpu) {
        super(assetCode, name, purchasePrice);
        this.ram = ram;
        this.cpu = cpu;
    }

    @Override
    public Double getMarketValue() {
        return getPurchasePrice() * (1 - depreciationRate);
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return super.toString() + "      RAM: " + ram + "      CPU: " + cpu;
    }
}
