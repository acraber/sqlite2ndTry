package adam.illhaveacompany.personalsqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListofDataIntoRecyclerView()

        btn_enter.setOnClickListener {
            addRecord()
        }
    }

    fun setupListofDataIntoRecyclerView(){
        if(getAllNames().size > 0) {
            recyclerView.layoutManager = LinearLayoutManager(this)

            //I need to find a way to make it getItemsList of the ID's, names and emails
            val itemAdapter = ItemAdapter(this, getAllNames())

            recyclerView.adapter = itemAdapter

        }
    }


    fun addRecord() {
        val name = et_name.text.toString()
        val email = et_email.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if(!name.isEmpty() && !email.isEmpty()) {

            val status = databaseHandler.addEmployee(EmpModelClass(0, name, email))

            if (status > -1) {
                Toast.makeText(applicationContext, "Record save attempted", Toast.LENGTH_LONG).show()
                et_name.text?.clear()
                et_email.text?.clear()
            }
        }else{
            Toast.makeText(applicationContext,"Record save attemped (with nothing in the database yet)", Toast.LENGTH_LONG).show()
        }
    }
/*
    private fun getItemsList() : ArrayList<EmpModelClass>{
       val databaseHandler : DatabaseHandler = DatabaseHandler(this)
        val empList : ArrayList<EmpModelClass> = databaseHandler.viewEmployee()
        return empList
    }//1
    */

    private fun getAllNames(): ArrayList<String> {
        val dbHandler = DatabaseHandler(this)
        val allNames = dbHandler.getAllNamesList()
        return allNames
    }//






}