@echo off

REM Các ID mới cho môi trường release
set RELEASE_INTER_SPLASH=12345678
set RELEASE_INTER_HOME=123456799
set RELEASE_NATIVE_HOME=23123213213

REM Đường dẫn đến file build.gradle (app level)
set BUILD_GRADLE=./app/build.gradle

REM Hàm để thay thế giá trị của buildConfigField
:replace_build_config_field
set file=%1
set field_name=%2
set new_value=%3

echo Replacing %field_name% with %new_value% in %file%

REM Sử dụng findstr để tìm dòng và replace để thay thế (khá phức tạp trong batch)
REM Cách tiếp cận này có thể không hoàn toàn mạnh mẽ như sed và có thể cần điều chỉnh
REM tùy thuộc vào định dạng chính xác của file build.gradle.

REM Tạo một file tạm để lưu kết quả
set temp_file=%TEMP%\temp_gradle_replace.tmp

REM Đọc file, tìm dòng và thay thế, ghi vào file tạm
type "%file%" | findstr /V /C:"buildConfigField(\"String\", \"%field_name%\", \"" > "%temp_file%"
echo buildConfigField("String", "%field_name%", "%new_value%") >> "%temp_file%"
type "%file%" | findstr /C:"buildConfigField(\"String\", \"%field_name%\", \"" | findstr /V /B > "%temp_file%"

REM Thay thế file gốc bằng file tạm
move /Y "%temp_file%" "%file%" > nul
goto :eof

echo Bắt đầu cấu hình ID cho môi trường release...

REM Thay thế inter_splash
call :replace_build_config_field "%BUILD_GRADLE%" "inter_splash" "%RELEASE_INTER_SPLASH%"

REM Thay thế inter_home
call :replace_build_config_field "%BUILD_GRADLE%" "inter_home" "%RELEASE_INTER_HOME%"

REM Thay thế native_banner
call :replace_build_config_field "%BUILD_GRADLE%" "native_banner" "%RELEASE_NATIVE_HOME%"

echo Hoàn tất cấu hình ID cho môi trường release.

del %TEMP%\temp_gradle_replace.tmp