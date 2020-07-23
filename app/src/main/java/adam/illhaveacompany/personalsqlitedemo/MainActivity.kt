package adam.illhaveacompany.personalsqlitedemo

import android.graphics.PointF.length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_enter.setOnClickListener {
            addRecord()
        }
    }

    fun setupListofDataIntoRecyclerView(){
        if(getItemsList().size > 0) {
            recyclerView.layoutManager = LinearLayoutManager(this)

            val itemAdapter = ItemAdapter(this, getItemsList())

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

    fun getItemsList() : ArrayList<EmpModelClass>{
       val databaseHandler : DatabaseHandler = DatabaseHandler(this)
        val seeEmployees : ArrayList<EmpModelClass> = databaseHandler.viewEmployee()
        return seeEmployees
    }//1






}