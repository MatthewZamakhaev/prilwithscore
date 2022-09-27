package ostinbslegb.solts

import ostinbslegb.solts.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var questionNo = 0
    var questions = listOf("What is the name of the football player whose transfer is considered the most expensive in history? \n\n " +
            "A) Neymar \n\n B) Mbappe \n\n C) Pogba",
        "Whose series of fights in UFC 29-0-0? \n\n " +
                "A) Conor McGregor \n\n B) Nate Diaz \n\n C) Khabib Nurmagomedov",
        "Who is the 'first racket' of the world today? \n\n " +
                "A) Novak Djokovic \n\n B) Daniil Medvedev \n\n C) Alexander Zverev",
        "How many Champions League cups does Real Madrid have? \n\n " +
                "A) 14 \n\n B) 12 \n\n C) 15",
        "Which cup in hockey is called a rolling one? \n\n " +
                "A) Gagarin Cup \n\n B) Stanley Cup \n\n C) Spengler Cup",
        "What is the name of the match between FC Barcelona and Real Madrid? \n\n " +
                "A) Derby of two capitals \n\n B) Der Klassiker \n\n C) El Classico",
        "Who got the Golden Ball in 2018? \n\n " +
                "A) Luka Modric \n\n B) Lionel Messi \n\n C) Cristiano Ronaldo",
        "Who was the founder of the modern Olympic Games? \n\n " +
                "A) Jean Claude Van Damme \n\n B) Pierre de Coubertin \n\n C) Olivier Schoenfelder",
        "What is the name of the professional basketball league in the USA? \n\n " +
                "A) NHL \n\n B) NBA \n\n C) NFL",
        "In what year did the Olympic Games take place in Sochi? \n\n " +
                "A) 2016 \n\n B) 2018 \n\n C) 2014",
        "What's your name? \n\n " +
                "A) John \n\n B) Bob \n\n C) Chandler")
    var rightAnswers = listOf(1, 3, 2, 1, 2, 3, 1, 2, 2, 3)
    var a = 0

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            showToast(1)
        }

        binding.button2.setOnClickListener {
            showToast(2)
        }

        binding.button3.setOnClickListener {
            showToast(3)
        }
    }


    fun showToast(answer: Int) {
        if (answer== rightAnswers[questionNo]) {
            updateQuestion()
            a++
            scores(a)
        } else {
            updateQuestion()
        }

    }

    fun updateQuestion() {
        questionNo += 1
        binding.textView.text = questions[questionNo]
        when(questionNo) {
            10 -> finally()
        }
    }
    @SuppressLint("SetTextI18n")
    fun scores(n: Int){
        binding.textView4.text = "Score: $n"
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    fun finally(){
        binding.textView.text = "You finished quiz! \n\n Congratulations!"
        binding.button.visibility = View.INVISIBLE
        binding.button2.text = "Restart"
        binding.button2.setOnClickListener {
            val mStartActivity = Intent(this@MainActivity, SplashActivity::class.java)
            val mPendingIntentId = 123456
            val mPendingIntent = PendingIntent.getActivity(
                this@MainActivity, mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val mgr = this@MainActivity.getSystemService(ALARM_SERVICE) as AlarmManager
            mgr[AlarmManager.RTC, System.currentTimeMillis() + 100] = mPendingIntent
            exitProcess(0)
        }
        binding.button3.visibility = View.INVISIBLE
    }

}