package my.android.myapp03

class Employee(var firstName: String, var lastName: String, var salary:Double)

interface Repository {
    var employees: List<Employee>
}

class RepositoryStub() : Repository {
    override var employees: List<Employee> = ArrayList<Employee>()
        get() {
            val emps = ArrayList<Employee>()
            emps.add(Employee("Yuri",  "Andrienko", 123456.0))
            emps.add(Employee("Vasya",  "Pupkin", 77777.0))
            emps.add(Employee("Masha",  "Mashkina", 300000.0))
            return  emps
        }
}