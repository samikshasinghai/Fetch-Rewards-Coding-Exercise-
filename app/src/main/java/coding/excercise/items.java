package coding.excercise;

public class items {

    int id;
    int listid;
    String name;

    public items(int listid, int id,  String name) {
        this.id = id;
        this.listid = listid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}


