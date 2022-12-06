# Server

## How to run the server

1. `server` 디렉토리로 이동

```bash
cd 2022-capstone-desgin-project/server
```

2. 의존성 라이브러리 설치

```bash
python3 -m pip install -r requirements.txt
```

3. `app.py` 실행

```bash
python3 app.py
```

4. 연결된 네트워크의 IP주소로 `android/app/src/main/java/com/example/capstoneandroid/stylusActivity.kt`의 77번 행 수정

```kotlin
// val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://192.168.0.16:5000")
val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://YOUR_IP_ADDRESS:5000")
```
