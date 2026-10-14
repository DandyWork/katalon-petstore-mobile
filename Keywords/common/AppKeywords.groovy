package common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable

public class AppKeywords {

    @Keyword
    static void launchApp() {
        String apkPath = RunConfiguration.getProjectDir() + '/' + GlobalVariable.G_ApkRelativePath
        Mobile.startApplication(apkPath, true, FailureHandling.STOP_ON_FAILURE)
        Mobile.waitForElementPresent(findTestObject('Login/btn_Login'), 20, FailureHandling.STOP_ON_FAILURE)
        Mobile.verifyElementVisible(findTestObject('Login/txt_Username'), 10, FailureHandling.STOP_ON_FAILURE)
    }

    @Keyword
    static void login(String username, String password) {
        Mobile.setText(findTestObject('Login/txt_Username'), username, 10)
        Mobile.setText(findTestObject('Login/txt_Password'), password, 10)
        Mobile.tap(findTestObject('Login/btn_Login'), 10)
    }
}