import java.util.ArrayList;
public class Book {

    private ArrayList<Person> names = new ArrayList<Person>();

    public Book() { }

    public int size(){
        return names.size();
    }

    public synchronized void add(String name,String number) {

        Person person = new Person(name,number);
        names.add(person);
    }

    public synchronized void add_number(String name,String number) {

        Person person;
        int index_person = 0;
        for(int index = 0; index < names.size(); index++){
            person = names.get(index);
            if(person.get_name().equals(name)){
                index_person = index;
                break;
            }
        }
        if(index_person != 0){
            Person person1 = names.get(index_person);
            person1.add_number(number);
        }
    }

    public synchronized Person get_person(int index) {
        return names.get(index);
    }

    public synchronized void reset() {
        names.clear();
    }
}