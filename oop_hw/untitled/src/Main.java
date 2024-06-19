import java.time.LocalDate;
import java.util.List;
import java.lang.Iterable;

public class Main {
    FamilyTree familyTree = new FamilyTree();


    Person grandMaPerson = new Person("Agafia", Gender.Female, LocalDate.of(1950, 7, 19));
    Person grandPaPerson = new Person("Michail", Gender.Male, LocalDate.of(1956, 1, 22));
    Person ivanPerson = new Person("Ivan", Gender.Male, LocalDate.of(1982, 9, 1));

}
