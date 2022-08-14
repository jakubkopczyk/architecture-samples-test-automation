# Android Architecture Blueprints v2 - TODO app test automation

Project is focused on test automation using Espresso and Cucumber

## Setup
New Features must be placed in `androidMockTest/assets/features/`. Subdirectories are allowed.

Prepared steps are placed in `androidMockTest/java/<packageName>/test/steps`.

Cucumber runner is placed in `androidMockTest/java/<packageName>/test`.

Emulator must be active or real device must be connected

### To run all End-to-End tests written with Cucumber
    ./gradlew connectedCheck -Pcucumber
You can run selected tags by using 

    ./gradlew connectedCheck -Pcucumber -Ptags="@smoke"

### Generating Cluecumber report
To download and generate report use

    ./gradlew downloadAndGenerateReport    
Cluecumber report is saved in `build/reports/cucumber/test`
Cucumber standard report is saved in `build/reports/cucumber`
If there will be any problem with generating report, please delete the previous report from the device using

    ./gradlew deleteExistingCucumberReports
### To run all End-to-End tests written without Cucumber (e.g. unit tests)
    ./gradlew connectedCheck
    
## Using an Android Studio IDE

1. Import the example to Android Studio: `File > Import Project`.
2. Create a test run configuration:
   1. Run > Edit Configurations
   2. Click `+` button and select Android Instrumented Tests
   3. Specify test name: `TodoTest`
   4. Select module: `androidTest`
   5. Enter a Specific instrumentation runner: `AndroidJUnitRunner`
   6. Click Ok
