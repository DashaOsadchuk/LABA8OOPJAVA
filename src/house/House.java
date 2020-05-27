package house;

public class House {
    private int id;
    private int Num;
    private float space;
    private int Floor;
    private int room;
    private String street;

    public House (int id, int Num, float space, int Floor, int room, String street)
    {
        this.id = id;
        this.Num = Num;
        this.space = space;
        this.Floor = Floor;
        this.room = room;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public float getSpace() {
        return space;
    }

    public void setSpace(float space) {
        this.space = space;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int Floor) {
        this.Floor = Floor;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getData() {
        return this.street + " " + this.id + " " + this.Num + " " + this.Floor + " " + this.room + " " + this.space + "\n";
    }

    public String toString(){
        return "House:" + " Id = " + id + "; Number = " + Num +
                "; Space(m^2) = " + space + "; Floor = " + Floor +
                "; Room's = " + room + "; Address = " + street  + ".";
    }
}