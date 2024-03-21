import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.taxi_app.MainActivity3
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.taxi_app.R

@RunWith(AndroidJUnit4::class)
class MainActivity3Test {

    private lateinit var scenario: ActivityScenario<MainActivity3>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity3::class.java)
        scenario.onActivity {}
    }

    @Test
    fun testEnableOkButtonWhenAllFieldsFilled() {
        scenario.onActivity { activity ->
            val startStreetEditText = activity.findViewById<EditText>(R.id.startStreetEditText)
            val startHouseEditText = activity.findViewById<EditText>(R.id.startHouseEditText)
            val startFlatEditText = activity.findViewById<EditText>(R.id.startFlatEditText)
            val destinationStreetEditText =
                activity.findViewById<EditText>(R.id.destinationStreetEditText)
            val destinationHouseEditText =
                activity.findViewById<EditText>(R.id.destinationHouseEditText)
            val destinationFlatEditText =
                activity.findViewById<EditText>(R.id.destinationFlatEditText)
            val okButton = activity.findViewById<Button>(R.id.okButton)

            startStreetEditText.setText("Street 1")
            startHouseEditText.setText("1")
            startFlatEditText.setText("A")
            destinationStreetEditText.setText("Street 2")
            destinationHouseEditText.setText("2")
            destinationFlatEditText.setText("B")

            assertTrue(okButton.isEnabled)
        }
    }

    @Test
    fun testDisableOkButtonWhenNotAllFieldsFilled() {
        scenario.onActivity { activity ->
            val startStreetEditText = activity.findViewById<EditText>(R.id.startStreetEditText)
            val startHouseEditText = activity.findViewById<EditText>(R.id.startHouseEditText)
            val startFlatEditText = activity.findViewById<EditText>(R.id.startFlatEditText)
            val destinationStreetEditText =
                activity.findViewById<EditText>(R.id.destinationStreetEditText)
            val destinationHouseEditText =
                activity.findViewById<EditText>(R.id.destinationHouseEditText)
            val destinationFlatEditText =
                activity.findViewById<EditText>(R.id.destinationFlatEditText)
            val okButton = activity.findViewById<Button>(R.id.okButton)

            startStreetEditText.setText("Street 1")
            startHouseEditText.setText("1")
            startFlatEditText.setText("A")
            destinationStreetEditText.setText("Street 2")
            destinationHouseEditText.setText("2")
            destinationFlatEditText.setText("")

            assertFalse(okButton.isEnabled)
        }
    }
}

