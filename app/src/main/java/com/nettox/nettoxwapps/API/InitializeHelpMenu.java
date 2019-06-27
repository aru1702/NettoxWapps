package com.nettox.nettoxwapps.API;

import android.content.Context;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;

public class InitializeHelpMenu {

    private static DbModel_HelpMenu model = new DbModel_HelpMenu();

    public static void initialize (Context context) {

        DbHelper_HelpMenu dbHelper = new DbHelper_HelpMenu(context);

        // About Us
        model.setId(1);
        model.setTitle("About Us");
        model.setImage(1);
        model.setSubtitle("Nettox Team");
        model.setDescription(
                "Team Leader:" +
                        "\nIrfan Budi" +
                        "" +
                        "\n\nHealth and science manager:" +
                        "\nNabila Ayu" +
                        "" +
                        "\n\nAndroid app developers:" +
                        "\nAlhuda Reza" +
                        "\nMuhamad Aldy" +
                        "" +
                        "\n\nContact e-mail:" +
                        "\nirfanbudi@gmail.com"
        );
        dbHelper.insertIntoHelpMenu(model);


        // How to use scan
        model.setId(2);
        model.setTitle("How to use scan");
        model.setImage(2);
        model.setSubtitle("In order to do scan, please connect your wearable device to the app first");
        model.setDescription(
                "1. From the main menu, choose the \"Analyze\" button." +
                        "\n\n2. Getting up your wearable device, make sure that you have connecting the app through the device." +
                        "\n\n3. Start by press the \"Let\'s Analyze!\" button." +
                        "\n\n4. Wait until the process is done." +
                        "\n\n5. You will be directed into the scan result." +
                        "\n\n\nIf your scan is failed, please try again."
        );
        dbHelper.insertIntoHelpMenu(model);

        // How to get scan result
        model.setId(3);
        model.setTitle("How to see your HRV");
        model.setImage(3);
        model.setSubtitle("This will help you how to see the history of your scan result");
        model.setDescription(
                "1. From the main menu, choose the \"Monitoring Result\" button." +
                        "\n\n2. Your scan results will be listed if exist." +
                        "\n\n3. To see the details, choose one of them and you will directed to the result details." +
                        "\n\n\nIf you can\'t see your scan results, please let us know by contact us through e-mail."
        );
        dbHelper.insertIntoHelpMenu(model);

        // How to read your HRV
        model.setId(4);
        model.setTitle("How to read your HRV");
        model.setImage(4);
        model.setSubtitle("This will help you how to read and understand of your scan result");
        model.setDescription(
                "1. You can access your scan result from \"Monitoring Result\" and select one of them to see the details." +
                        "\n\n2. There you can see your HRV value which reflect the condition of your body." +
                        "\n\n3. Below you can see BPM Average, beats per minutes, which your heart beat minute." +
                        "\n\n4. Comment on the below can help you to know your body condition and gives you some advices to help you more."
        );
        dbHelper.insertIntoHelpMenu(model);
    }

}
