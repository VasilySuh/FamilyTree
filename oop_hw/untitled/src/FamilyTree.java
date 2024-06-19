import java.util.*;

public class FamilyTree implements Iterable {
    private long personId;
    private List<Person> personList;

    public FamilyTree() {
        this(new ArrayList<>());
    }
    public FamilyTree(List<Person> personList){
        this.personList = personList;
    }
    public boolean add(Person person){
        if(person == null){
            return false;
        }
        if (!personList.contains(person)){
            personList.add(person);
            person.setId(personId++);

            AddToParents(person);
            AddToChildren(person);
            return true;
        }
        return false;
    }

    private void AddToParents(Person person){
        for (Person parent: person.getParents()){
            parent.addChild(person);
        }
    }

    private void AddToChildren(Person person){
        for (Person child: person.getChildren()){
            child.addParent(person);
        }
    }

    public List<Person> getSiblings(int id){
        Person person = getById(id);
        if (person == null){
            return null;
        }
        List<Person> res = new ArrayList<>();
        for (Person parent: person.getParents()){
            for(Person child: parent.getChildren()){
                if (!child.equals(person)) {
                    res.add(child);
                }
            }
        }
        return res;
    }

    public List<Person> getByName(String name){
        List<Person> res = new ArrayList<>();
        for (Person person: personList){
            if (person.getName().equals(name)){
                res.add(person);
            }
        }
        return res;
    }

    public boolean setWedding(long personId1, long personId2){
        if (checkId(personId1) && checkId(personId2)){
            Person person1 = getById(personId1);
            Person person2 = getById(personId2);
            if (person1.getSpouse() == null && person2.getSpouse() == null){
                person1.setSpouse(person2);
                person2.setSpouse(person1);
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean setDivorce(long personId1, long personId2){
        if (checkId(personId1) && checkId(personId2)){
            Person person1 = getById(personId1);
            Person person2 = getById(personId2);
            if (person1.getSpouse() != null && person2.getSpouse() != null){
                person1.setSpouse(null);
                person2.setSpouse(null);
            }
            else{
                return false;
            }
        return false;
        }
        return false;
    }

    public boolean remove(long personId){
        if (checkId(personId)){
            Person person = getById(personId);
            return personList.remove(person);
        }
        return false;
    }

    private boolean checkId(long id){
        return id < personId && id >= 0;
    }

    public Person getById(long id){
        for (Person person: personList){
            if(person.getId() == id){
                return person;
            }
        }
        return null;
    }

    @Override
    public Iterator<Person> iterator(){
        return personList.iterator();
    }

    public void sortByName() {
        Collections.sort(personList, Comparator.comparing(Person::getName));
    }

    public void sortByBirthDate() {
        Collections.sort(personList, Comparator.comparing(Person::getBirthDate));
    }

    @Override
    public String toString(){
        return getInfo();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("in tree");
        sb.append(personList.size());
        sb.append(" objects: \n");
        for (Person person: personList){
            sb.append(person);
            sb.append("\n");
        }
        return "";
    }
}
