import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taxi_app.MainActivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.taxi_app.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {}
    }

    @Test
    fun testEnableRegistrationButtonWhenAllFieldsFilled() {
        scenario.onActivity { activity ->
            val phoneEditText = activity.findViewById<EditText>(R.id.phoneEditText)
            val nameEditText = activity.findViewById<EditText>(R.id.nameEditText)
            val surnameEditText = activity.findViewById<EditText>(R.id.surnameEditText)
            val registrationButton = activity.findViewById<Button>(R.id.registrationButton)

            phoneEditText.setText("123456789")
            nameEditText.setText("John")
            surnameEditText.setText("Doe")

            assertTrue(registrationButton.isEnabled)
        }
    }

    @Test
    fun testDisableRegistrationButtonWhenNotAllFieldsFilled() {
        scenario.onActivity { activity ->
            val phoneEditText = activity.findViewById<EditText>(R.id.phoneEditText)
            val nameEditText = activity.findViewById<EditText>(R.id.nameEditText)
            val surnameEditText = activity.findViewById<EditText>(R.id.surnameEditText)
            val registrationButton = activity.findViewById<Button>(R.id.registrationButton)

            phoneEditText.setText("123456789")
            nameEditText.setText("")
            surnameEditText.setText("Doe")

            assertFalse(registrationButton.isEnabled)
        }
    }
}
