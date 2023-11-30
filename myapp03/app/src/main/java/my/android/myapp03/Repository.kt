package my.android.myapp03

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL

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

class RepositoryWebService(context: Context) : Repository {
    override var employees: List<Employee> = ArrayList<Employee>()
        get() {
            val url = URL("http://yand.dyndns.org/api/employees.aspx")
            val connection = url.openConnection()
            val text = connection.getInputStream().bufferedReader().readText()
            println(text)
            val gson = Gson()
            var emps = arrayOf<Employee>()
            emps = gson.fromJson(text, emps::class.java)

            Thread.sleep(1)

            return  emps.toList()
        }
}

class RepositoryCBR(val context: Context) : Repository {
    override var employees: List<Employee> = ArrayList<Employee>()
        get() {
            val emps =  ArrayList<Employee>()

            val url = URL("https://www.cbr-xml-daily.ru/daily_json.js")
            val connection = url.openConnection()
            val text = connection.getInputStream().bufferedReader().readText()
            println(text)
            val gson = Gson()
            val result = gson.fromJson(text, JsonObject::class.java)
            val valute = result.getAsJsonObject("Valute")
            for(currency in valute.keySet()) {
                val node = valute.getAsJsonObject(currency)
                val name = node.getAsJsonPrimitive("Name").asString
                val value = node.getAsJsonPrimitive("Value").asDouble
                emps.add(Employee(currency, name, value))
            }
            return  emps
        }
}

class RepositoryAsset(val context: Context) : Repository {
    override var employees: List<Employee> = ArrayList<Employee>()
        get() {
            val text = context.assets.open("emps.json").bufferedReader().readText()

            println(text)
            val gson = Gson()
            var emps = arrayOf<Employee>()
            emps = gson.fromJson(text, emps::class.java)

            Thread.sleep(1)

            return  emps.toList()
        }
}

class RepositoryFile(val context: Context) : Repository {
    override var employees: List<Employee> = ArrayList<Employee>()

        // TODO Написать конструктор, который, если файла emps.json нет,
        // создаст его и скопирует в него содержмое emps.json из asssets

        get() {
            val file = File(context.filesDir, "emps.json")
            //val text = FileInputStream(file).bufferedReader().readText()

            var text = "[]"
            FileInputStream(file).bufferedReader().use {
                text = it.readText()
            }

            println(text)
            val gson = Gson()
            var emps = arrayOf<Employee>()
            emps = gson.fromJson(text, emps::class.java)

            Thread.sleep(1)

            return  emps.toList()
        }
}

class RepositoryDB(val context: Context) : Repository {
    init {
        if (!context.getDatabasePath("Emps").exists()) {
            val db = context.openOrCreateDatabase("Emps", Context.MODE_PRIVATE, null)
            var sql =
                "CREATE TABLE IF NOT EXISTS people(firstName VARCHAR, lastName VARCHAR, salary Float)"
            db.execSQL(sql)
            sql =
                "INSERT INTO people(firstName, lastName, salary) VALUES ('Yuri', 'Yurkov', 123456.0)"
            db.execSQL(sql)
        }
    }
    override var employees: List<Employee> = ArrayList<Employee>()

        get() {
            val emps =  ArrayList<Employee>()
            val db = context.openOrCreateDatabase("Emps", Context.MODE_PRIVATE, null)
            val sql = "SELECT * FROM people"
            val cursor = db.rawQuery(sql,null)
            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                emps.add(Employee(cursor.getString(0),cursor.getString(1), cursor.getDouble(2)))
                cursor.moveToNext()
            }
            return  emps
        }
}