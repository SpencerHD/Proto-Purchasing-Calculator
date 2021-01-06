public class itemsList {

    private String name = null;
    private String diff = null;
    private String quant = null;
    private String tot = null;

    public itemsList() {
    }

    public itemsList(String name, String diff, String quant, String tot) {
        this.name = name;
        this.diff = diff;
        this.quant = quant;
        this.tot = tot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getTot() {
        return tot;
    }

    public void setTot(String tot) {
        this.tot = tot;
    }
}