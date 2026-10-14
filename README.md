# katalon-petstore-mobile

Mobile automation project built with **Katalon Studio** for the **Swag Labs** Android demo app (`APK-sauce-labs.2.7.1.apk`). It automates the main user flows of the app: login, add to cart, logout, and purchase.

## Project Overview

- **Tool:** Katalon Studio (Mobile, Android)
- **App under test:** Swag Labs mobile app (package `com.swaglabsmobileapp`)
- **APK location:** `Apps/APK-sauce-labs.2.7.1.apk` (stored inside the project)
- **Test objects:** created manually with Mobile Spy (no Record Mobile), organized by screen in the Object Repository
- **No hardcoded paths:** the APK path is built at runtime from the project directory and a Global Variable

## Project Structure

```
katalon-petstore-mobile/
├── Apps/                      # APK file
├── Object Repository/         # Mobile test objects (Login, Menu, Common, Products, Cart, Checkout)
├── Test Cases/                # Test case scripts
├── Test Suites/               # Test suite that runs all test cases
├── Keywords/common/           # AppKeywords: launchApp(), login(username, password)
├── Profiles/default.glbl      # Global Variables
└── Reports/                   # Generated execution reports
```

## Prerequisites

- Katalon Studio (Mobile testing, Android)
- Android Studio with an Android emulator (AVD) or a physical device with USB debugging on
- `ANDROID_HOME` pointing to the Android SDK
- Device visible in `adb devices`

## Configuration

Global Variables are defined in **Profiles → default**:

| Variable | Description |
|---|---|
| `G_ApkRelativePath` | APK path relative to the project root (`Apps/APK-sauce-labs.2.7.1.apk`) |
| `G_ValidUser` | Valid username |
| `G_LockedUser` | Locked-out username (negative scenario) |
| `G_Password` | Password for the demo users |
| `G_WrongPassword` | Invalid password (negative scenario) |
| `G_FirstName`, `G_LastName`, `G_Zip` | Checkout form data |

The APK is launched with a project-relative path, so the project works from any folder or machine:

```groovy
String apkPath = RunConfiguration.getProjectDir() + '/' + GlobalVariable.G_ApkRelativePath
Mobile.startApplication(apkPath, true)
```

## How to Run

1. Start the Android emulator (or connect a device) and confirm it appears in `adb devices`.
2. Open the project in Katalon Studio (**File → Open Project**, select the `katalon-petstore-mobile` folder).
3. Make sure the execution profile is **default**.
4. In **Test Suites**, open `TS_Mobile_Regression`.
5. Select **Android** and the target device in the run configuration.
6. Click **Run**.

To run a single test case, open it and click **Run**, with Android and the device selected.

## Test Suite

| Test Suite | Contains |
|---|---|
| `TS_Mobile_Regression` | All test cases listed below, each launching the app fresh so they run independently |

## Test Coverage

| Test Case | Feature | Type | Scenario | Expected Result |
|---|---|---|---|---|
| `Login_Valid` | Login | Positive | Login with a valid user | Products screen is displayed |
| `Login_LockedOut` | Login | Negative | Login with a locked-out user | Error message is displayed, user stays on the login screen |
| `AddToCart_Logout` | Add to cart, Logout | Positive | Add a product to the cart, then log out | Cart page opens with the item, login screen shown after logout |
| `Purchase_E2E` | Purchase | Positive | Login, add a product, checkout, fill in details, finish the order | Order confirmation screen is displayed |

Features covered: **Login**, **Logout**, **Add to cart**, **Purchase**. Both positive and negative scenarios are included.

## Reports

After a run, the report is generated in the `Reports/` folder of the project. It can be exported as HTML or PDF from the Report view in Katalon Studio.

## Notes

- Locators use `content-desc` wherever available, and `text` only for labels with fixed text.
- Screens are verified with wait and assertion steps before interacting, to avoid flaky tests.
