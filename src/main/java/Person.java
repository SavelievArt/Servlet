public class Person {
    private String name;
    private String number;

    public Person(String name,String number){
        this.name=name;
        this.number=number;
    }
    public void add_number(String number){
        this.number=this.number+' '+number;
    }

    public String get_number(){
       return number;
    }

    public String get_name(){
        return name;
    }
}
