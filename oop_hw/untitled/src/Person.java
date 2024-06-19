import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private long id;
    private String name;
    Person mother, father;
    private List<Person> children;
    private List<Person> parents;
    private LocalDate birthDate;
    private static LocalDate deathDate;
    private Gender gender;
    private Person spouse;

    public Person(String name, Person mother, Person father, LocalDate birthDate, LocalDate deathDate, Gender gender){
        id = -1;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        parents = new ArrayList<>();
        if(father != null){
            parents.add(father);
        }
        if(mother != null){
            parents.add(mother);
        }
        List<Person> children = new ArrayList<>();
    }

    public Person(String name, Gender gender, LocalDate birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
//                LocalDate deathDate: null,
//                father: null,
//                mother: null);
    }

    public Person(String name, Gender gender, LocalDate birthDate, Person father, Person mother){
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate =  null;
        this.father = father;
        this.mother = mother;
    }

    public boolean addChild(Person child){
        if(!children.contains(child)){
            children.add(child);
            return true;
        }
        return false;
    }

    public void addParent(Person parent) {
        if(parent != null) {
            parents.add(parent);
        }
    }

    public Person getFather(){
        for(Person parent: parents){
            if(parent.getGender() == Gender.Male){
                return parent;
            }
        }
        return null;
    }

    public Person getMother(){
        for (Person parent: parents){
            if(parent.getGender() == Gender.Female){
                return parent;
            }
        }
        return null;
    }

    public int getAge(){
        if(deathDate == null){
            return getPeriod(birthDate, LocalDate.now());
        }
        else {
            return getPeriod(birthDate, deathDate);
        }
    }

    private int getPeriod(LocalDate birthDate, LocalDate deathDate){
        Period diff = Period.between(birthDate, deathDate);
        return diff.getYears();
    }



    public List<Person> getChildren() {
        return children;
    }

    public String getName(){
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString(){
        return getInfo();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(id);
        sb.append(", name: ");
        sb.append(name);
        sb.append(", gender: ");
        sb.append(getGender());
        sb.append(", age: ");
        sb.append(getAge());
        sb.append(", ");
        sb.append(getSpouseInfo());
        sb.append(", ");
        sb.append(getMotherInfo());
        sb.append(", ");
        sb.append(getFatherInfo());
        sb.append(", ");
        sb.append(getChildrenInfo());
        return sb.toString();
    }

    public String getSpouseInfo(){
        String res = "spouse: ";
        if(spouse == null){
            res += "none";
        }
        else{
            res += spouse.getName();
        }
        return res;
    }

    public String getMotherInfo(){
        String res = "mother";
        Person mother = getMother();
        if(mother != null){
            res += mother.getName();
        }
        else{
            res += "unknown";
        }
        return res;
    }

    public String getFatherInfo(){
        String res = "father";
        Person father = getFather();
        if(father != null){
            res += father.getName();
        }
        else{
            res += "unknown";
        }
        return res;
    }

    public String getChildrenInfo(){
        StringBuilder res = new StringBuilder();
        res.append("children: ");
        if (children.size() != 0){
            res.append(children.get(0).getName());
            for(int i = 1; i < children.size(); i++){
                res.append(", ");
                res.append(children.get(1).getName());
            }
        }
        else{
            res.append("nobody");
        }
        return res.toString();
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(!(obj instanceof Person)){
            return false;
        }
        Person person = (Person) obj;
        return person.getId() == getId();
    }
}
