package com.example.firstxmlprojectainotepad.presentation.features.Notes_Screen


import android.os.Build
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.ActivityNotesScreenBinding

class Notes_Screen_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener on menu icon
        binding.dropdownMenu.setOnClickListener {
            val popup = PopupMenu(this, binding.dropdownMenu)
            popup.menuInflater.inflate(R.menu.template_menu, popup.menu)

            // Optional: Show icons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popup.setForceShowIcon(true)
            }

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.work_edit -> {
                        Toast.makeText(this, "Work clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.office_edit -> {
                        Toast.makeText(this, "Office clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.shopping_edit -> {
                        Toast.makeText(this, "Shopping clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            popup.show()
        }
    }
}





//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.example.firstxmlprojectainotepad.R
//import com.example.firstxmlprojectainotepad.databinding.ActivityNotesScreenBinding
//
//class Notes_Screen_Activity : AppCompatActivity() {
//    private lateinit var binding :ActivityNotesScreenBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityNotesScreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        enableEdgeToEdge()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            popup.setForceShowIcon(true)
//        }
//        holder.binding.menuIcon.setOnClickListener {
//            val popup = PopupMenu(holder.itemView.context, holder.binding.menuIcon)
//            popup.menuInflater.inflate(R.menu.template_menu, popup.menu)
//
//            popup.setOnMenuItemClickListener { menuItem ->
//                when (menuItem.itemId) {
//                    R.id.action_edit -> {
//                        // Handle edit
//                        Toast.makeText(context, "Edit clicked", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    R.id.action_delete -> {
//                        // Handle delete
//                        Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show()
//                        true
//                    }
//                    else -> false
//                }
//            }
//
//            popup.show()
//        }
//
//    }
//}