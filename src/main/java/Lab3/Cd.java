package Lab3;

import javax.persistence.*;

@Entity
@Table(name = "cd")
public class Cd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "CD_NAME", nullable = false, length = 40)
    private String cdName;

    @Column(name = "BAND_NAME", nullable = false, length = 40)
    private String bandName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

}