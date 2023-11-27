//from try.kotlinlang.org

fun main() {
    println("Hello, I am Kotlin!")
    var s1: String = "I am s1"
    val s2 = "I am s2"
    var s3 = s1 + " " + s2
    println(s3)
    val s1_last = s1.split(" ")
    println(s1_last[2])
    s1 = "qwerty"
//     s2 = "qwerty" // error, из-за val
    
//     Null safety
    var s4: String = "abc";
    println(s4.toUpperCase())
    var s5: String?
    s5 = null
    s5 = "qwerty"
    println((s5 as String?)?.toUpperCase())
    
    var people1 = ArrayList<String>()
    people1.add("YUri"); people1.add("Vasya"); people1.add("Masha");
    
    for(p in people1) {
        println(p)
    }
    
    var people = ArrayList<Person>()
    people.add(Person("Yuri", "Andrienko"))
    people.add(Person("Vasya", "Pupkin"))
    people.add(Person("Masha", "Mashkina"))
    
    for(p in people) {
        println(p.lastname)
        println(p)
    }
    
    var e1 = Employee("Bill", "Gates", 123456.0)
    println(e1)
    
}

open class Person(firstname: String, lastname: String) {
        public var firstname: String = firstname
    	get() {
            return field
        }
        set(value) {
            field = value
        }
        
    public var lastname: String = lastname
    	get() {
            return field
        }
        set(value) {
            field = value
        }
    
    override fun toString(): String {
        return this.firstname + " " + this.lastname
    } 
}


class Employee(firstname: String, lastname: String, var salary: Double):
						Person(firstname, lastname) {
                            
override fun toString(): String {
        return "$firstname $lastname - $salary"
    } 
                            
 }