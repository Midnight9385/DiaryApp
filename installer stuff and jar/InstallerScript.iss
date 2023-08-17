[Setup]
AppName=Diary App
AppVerName=1.0
DefaultDirName={commonpf}\DiaryApp
OutputBaseFilename=DiaryAppInstaller
PrivilegesRequired = lowest

[Files]
Source: "C:\Users\Zachary\Documents\GitHub\diaryapp\diaryapp.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Zachary\Documents\jdk-20_windows-x64_bin\jdk-20.0.2\bin\java.exe";  DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Zachary\Documents\GitHub\diaryapp\src\main\resources\DiaryIcon.ico"; DestDir: "{app}";
[Icons]
//Name: "{group}\Diary App"; Filename: "{app}\java.exe"; Parameters: "-jar {app}\DiaryApp.jar"
//Name: "{commondesktop}\Diary App"; Filename: "{app}\java.exe"; Parameters: "-jar {app}\DiaryApp.jar"; IconFilename: "{app}\DiaryIcon.ico"

[Run]
Filename: "{app}\java.exe"; Parameters: "-jar {app}\DiaryApp.jar"; WorkingDir: "{app}";

[UninstallDelete]
Type: files; Name: "{app}\*.*"
Type: dirifempty; Name: "{app}"
//Type: icons; Name: "{group}\Diary App"

