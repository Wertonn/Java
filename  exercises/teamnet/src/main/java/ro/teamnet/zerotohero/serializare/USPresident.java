package ro.teamnet.zerotohero.serializare;

import java.io.Serializable;

/**
 * Created by Buli on 28.10.2014.
 */
public class USPresident implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String period;
    private transient String term;

    @Override
    public String toString() {
        return "US President [name=" + name + ", period=" + period + ", term=" + term + "]";
    }
    public USPresident(String name, String period, String term) {
        this.name = name;
        this.period = period;
        this.term = term;
    }

}