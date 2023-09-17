package OrganProject;

public class Organ{

    private String name;
    private String condition;

    public Organ(String name, String condition){

        this.name = name;
        this.condition = condition;

    }

    public String getName(){
        return this.name;
    }

    public String getCondition(){
        return this.condition;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }

    public void display(){
        System.out.println("Name: " + this.getName());
        System.out.println("Condition: " + this.getCondition());
    }
}
