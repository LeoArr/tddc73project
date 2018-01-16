package tddc73.leopo.project1;

import android.os.SystemClock;

import java.util.Random;

/**
 * Created by leopo on 2017-12-15.
 */

public class MockPC implements PasswordChecker {
    @Override
    public int getMinNumberOfCharacters() {
        return 4;
    }

    @Override
    public float checkPassword(String password) {
        return ((float) Math.max(password.length() - 4, 0)) / 8.0f;
    }

    public MockPC() {}
}
