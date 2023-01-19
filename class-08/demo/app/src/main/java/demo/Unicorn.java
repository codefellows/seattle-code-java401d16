package demo;

import java.util.List;

public class Unicorn {
    private Integer hornLength;
    private Integer hooves;
    private boolean isMajestic;
    List<String> colors;

    public Unicorn(Integer hornLength, Integer hooves, boolean isMajestic, List<String> colors) {
        this.hornLength = hornLength;
        this.hooves = hooves;
        this.isMajestic = isMajestic;
        this.colors = colors;
    }

    public String fly(){
        return "Fly fly away";
    }

    public Integer getHornLength() {
        return hornLength;
    }

    public void setHornLength(Integer hornLength) {
        this.hornLength = hornLength;
    }

    public Integer getHooves() {
        return hooves;
    }

    public void setHooves(Integer hooves) {
        this.hooves = hooves;
    }

    public boolean isMajestic() {
        return isMajestic;
    }

    public void setMajestic(boolean majestic) {
        isMajestic = majestic;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
