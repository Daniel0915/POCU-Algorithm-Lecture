package academy.pocu.comp3500.lab3.app;

import static academy.pocu.comp3500.lab3.MissionControl.findMaxAltitudeTime;

import academy.pocu.comp3500.lab3.MissionControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        {
            final int[] altitudes = new int[] { 10, 9, 7, 6, 4, 3, 2, 1, 0};

            assert (findMaxAltitudeTime(altitudes) == 0);
        }


    }
}
