/**
 * Created by Jeff on 9/18/2014.
 */
public class DatabaseEntry {
    public int id;
    public String name;
    public String description;

    public String toString() {
        return name;
    }

    public DatabaseEntry(int _id, String _name) {
        this(_id, _name, null);
    }
    public DatabaseEntry(int _id, String _name, String _description) {
        id = _id;
        name = _name;
        description = _description;
    }

    public boolean equals(DatabaseEntry other) {
        return this.id == other.id && this.name.equalsIgnoreCase(other.name);
    }
}
