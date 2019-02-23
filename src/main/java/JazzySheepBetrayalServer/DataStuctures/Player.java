package JazzySheepBetrayalServer.DataStuctures;

public class Player {
    private String id;
    private Type type;


    public Player(String id, Type type){
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Player)){
            return false;
        }
        Player other = (Player) obj;
        if(this.getId().equals(other.getId())){
            return true;
        }else{
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }


}
