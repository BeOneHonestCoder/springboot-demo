import com.net.domain.Student
import spock.lang.Specification


class StudentTest extends Specification {

    def "get ID"() {
        setup:
        def student = new Student();
        student.setId(1);
        def expectedId;

        when:
        expectedId = student.getId();

        then:
        expectedId == 1;
    }
}