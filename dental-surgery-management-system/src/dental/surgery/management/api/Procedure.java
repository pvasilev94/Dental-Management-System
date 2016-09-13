package dental.surgery.management.api;

public class Procedure {

    int id;
    String name;
    double cost;

    public Procedure(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public Procedure() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {

        return new StringBuffer(" id : ").append(this.id).append(" name : ")
                .append(this.name).append(" cost : ").append(this.cost)
                .toString();

    }
}
