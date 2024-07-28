import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Employee mehmet = new Employee("Mehmet", "Beken", 27, BigDecimal.valueOf(100.50));
        Employee melisa = new Employee("Melisa", "Sürmeli", 29, BigDecimal.valueOf(150.99));
        Employee hasan = new Employee("Hasan", "Yılmaz", 25, BigDecimal.valueOf(50.50));
        Employee ayse = new Employee("Ayşe", "Doğan", 20, BigDecimal.valueOf(75.99));
        Employee alper = new Employee("Alper", "Kaya", 35, BigDecimal.valueOf(250.00));
        Employee mehmet2 = new Employee("Mehmet", "Demir", 45, BigDecimal.valueOf(400.00));

        List<Employee> employeeList = List.of(mehmet, melisa, hasan, ayse, alper, mehmet2);


        //I want to sort the workers according to their salaries.
        //SORT
        List<Employee> employeeListSortBySalary = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary)).toList();
        for (Employee employee : employeeListSortBySalary) {
            System.out.println("Employee : " + employee.getName() + " -> Salary = " + employee.getSalary());
        }

        //I want to filter Those whose salary is more than 200
        //FILTER
        List<Employee> employeeListFilterBySalaryMoreThan120 = employeeList.stream().filter(employee -> employee.getSalary().compareTo(BigDecimal.valueOf(120.00)) > 0).toList();
        for (Employee employee : employeeListFilterBySalaryMoreThan120) {
            System.out.println("Employee : " + employee.getName() + " -> Salary = " + employee.getSalary());
        }

        // I want to employe name add empty list
        //MAP
        List<String> employeeNameList = employeeList.stream().map(Employee::getName).toList();
        for (String name : employeeNameList) {
            System.out.println(name);
        }

        //I want to find the total of salaries
        //REDUCE
        BigDecimal sumSalary = employeeList.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumSalary);

        //I want to increase salaries by 20 percent
        //PEEK
        employeeList.stream().peek(employee -> employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.2)))).toList();
        for (Employee employee : employeeList) {
            System.out.println("Employee : " + employee.getName() + " -> Salary = " + employee.getSalary());
        }

        //I want to increase salaries by 20 percent and I use paralel stream
        //PEEK
        employeeList.parallelStream().peek(employee -> employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.2)))).toList();
        for (Employee employee : employeeList) {
            System.out.println("Employee : " + employee.getName() + " -> Salary = " + employee.getSalary());
        }

        // If there is an employee with the same name, I do not want to include the second name on the list.
        //distinct
        List<Employee> distinctEmployeesByName = employeeList.stream()
                .collect(Collectors.toMap(
                        Employee::getName, // Her bir çalışan için anahtar olarak name alanını kullan
                        Function.identity(),    // Her bir çalışanı kendi değeri olarak sakla
                        (existing, replacement) -> existing // Aynı anahtara sahip iki değer olduğunda ilkini seç
                ))
                .values() // Benzersiz çalışanlardan oluşan Collection elde et
                .stream() // Tekrar stream'e dönüştür
                .toList(); // List olarak topla

        System.out.println(distinctEmployeesByName);
    }

}

