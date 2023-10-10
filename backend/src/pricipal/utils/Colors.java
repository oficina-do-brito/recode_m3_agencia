package pricipal.utils;

public enum Colors {
	BLACK("\u001B[30m"),
    YELLOW("\u001B[33m"),
    VERMELHO("\u001B[31m"),
    AZUL("\u001B[34m"),
    RESET("\u001B[0m");

    private String color;

    Colors(String cor){
        this.color = cor;
    }

     public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
