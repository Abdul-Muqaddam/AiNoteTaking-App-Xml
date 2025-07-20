package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.CalenderMonthBinding
import com.example.firstxmlprojectainotepad.databinding.FragmentCalenderBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.Locale

class CalenderFragment : Fragment() {
    private lateinit var binding: FragmentCalenderBinding
    private var currentMonth: YearMonth = YearMonth.now()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalenderBinding.inflate(layoutInflater)

        val firstMonth = currentMonth.minusMonths(12)
        val lastMonth = currentMonth.plusMonths(12)
        val firsDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

        binding.calendarView.setup(
            startMonth = firstMonth,
            endMonth = lastMonth,
            firstDayOfWeek = firsDayOfWeek
        )
        binding.calendarView.scrollToMonth(currentMonth)
        binding.calendarView.monthScrollListener = { month ->
            currentMonth = month.yearMonth
        }

        binding.calendarView.dayBinder = object : MonthDayBinder<DateViewContainer> {
            override fun create(view: View): DateViewContainer {
                return DateViewContainer(view)
            }

            override fun bind(container: DateViewContainer, data: CalendarDay) {
                container.textView.text = data.date.dayOfMonth.toString()
                if (data.position == DayPosition.MonthDate) {
                    container.textView.visibility = View.VISIBLE
                } else {
                    container.textView.visibility = View.GONE
                }
            }
        }


        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View): MonthViewContainer {
                    return MonthViewContainer(
                        view,
                        calendarView = binding.calendarView,
                        getCurrentMonth = { currentMonth },
                        updateCurrentMonth = { newMonth -> currentMonth = newMonth }
                    )
                }

                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    val monthName =
                        month.yearMonth.month.name.lowercase().replaceFirstChar { it.uppercase() }
                    val year = month.yearMonth.year
                    container.textView.text = "$monthName $year"
                }
            }


        return binding.root
    }

    inner class DateViewContainer(view: View) : ViewContainer(view) {
        val textView: TextView = view.findViewById(R.id.calendarDatesText)
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView: TextView = view.findViewById(R.id.calendarDatesText)
    }

    inner class MonthViewContainer(
        view: View,
        private val calendarView: com.kizitonwose.calendar.view.CalendarView,
        private val getCurrentMonth: () -> YearMonth,
        private val updateCurrentMonth: (YearMonth) -> Unit
    ) : ViewContainer(view) {
        var binding = CalenderMonthBinding.bind(view)
        val textView: TextView = binding.monthHeaderText

        init {
            binding.calenderLeftArrow.setOnClickListener {
                val previousMonth = getCurrentMonth().minusMonths(1)
                updateCurrentMonth(previousMonth)
                calendarView.smoothScrollToMonth(previousMonth)
            }

            binding.calenderRightArrow.setOnClickListener {
                val nextMonth = getCurrentMonth().plusMonths(1)
                updateCurrentMonth(nextMonth)
                calendarView.smoothScrollToMonth(nextMonth)
            }
        }

//        val leftArrow=binding.calenderLeftArrow.setOnClickListener { Log.d("left","clcked") }
//        val rightArrow=binding.calenderRightArrow.setOnClickListener { Log.d("left","clcked") }

    }
}