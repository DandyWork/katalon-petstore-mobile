import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

CustomKeywords.'common.AppKeywords.launchApp'()

CustomKeywords.'common.AppKeywords.login'(GlobalVariable.G_ValidUser, GlobalVariable.G_Password)

Mobile.waitForElementPresent(findTestObject('Products/lbl_Title'), 15)

Mobile.tap(findTestObject('Products/btn_AddToCart'), 10)

Mobile.tap(findTestObject('Common/btn_Cart'), 10)

Mobile.tap(findTestObject('Cart/btn_Checkout'), 10)

// confirm we are on the checkout information screen
Mobile.waitForElementPresent(findTestObject('Checkout/lbl_CheckoutInfo'), 15)

Mobile.verifyElementVisible(findTestObject('Checkout/lbl_CheckoutInfo'), 10)

Mobile.verifyElementText(findTestObject('Checkout/lbl_CheckoutInfo'), 'CHECKOUT: INFORMATION')

Mobile.setText(findTestObject('Checkout/txt_FirstName'), GlobalVariable.G_FirstName, 10)

Mobile.setText(findTestObject('Checkout/txt_LastName'), GlobalVariable.G_LastName, 10)

Mobile.setText(findTestObject('Checkout/txt_Zip'), GlobalVariable.G_Zip, 10)

Mobile.hideKeyboard()

Mobile.tap(findTestObject('Checkout/btn_Continue'), 10)

Mobile.scrollToText('FINISH' // finish button is often below the fold
    )

Mobile.tap(findTestObject('Checkout/btn_Finish'), 10)

Mobile.verifyElementVisible(findTestObject('Checkout/lbl_Complete'), 10)

Mobile.tap(findTestObject('Checkout/btn_BackHome'), 0)

Mobile.waitForElementPresent(findTestObject('Products/lbl_Title'), 15)

Mobile.closeApplication()

