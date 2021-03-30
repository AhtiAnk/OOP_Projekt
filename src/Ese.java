import java.util.List;
import java.util.Objects;

public class Ese {
    //esemed
    private String nimi;

    public Ese(String nimi) {
        this.nimi = nimi;
    }

    @Override  //Equals meetod, et saaks esemete listist esemeid eemaldada
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ese ese = (Ese) o;
        return nimi.equals(ese.nimi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nimi);
    }

    public String getNimi() {
        return nimi;
    }

    public String toString(){
        return nimi;
    }

}
