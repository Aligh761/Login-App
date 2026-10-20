# 🔐 LoginApp

A small Android demo app that shows a **passwordless email login** flow. Enter your email, get a code, confirm it, and you're in! ✨

> 🧪 This is a demo project made for learning, not for production use.

---

## ✨ Features

- 📧 Login with email and a verification code
- ✅ Validation for email format and the 6-digit code
- 📱 Device identification using the Android ID
- ⚠️ Friendly error messages from the server
- ⏳ Buttons are disabled while a request is running
- 🏠 Redirects to the home screen after a successful login

---

## 🛠️ Tech Stack

| | |
|---|---|
| 🧩 Language | Kotlin |
| 🏗️ Architecture | MVP |
| 🌐 Networking | Retrofit + Gson |
| ⚡ Async | Kotlin Coroutines |
| 🎨 UI | View Binding, Material Components |

---

## 📂 Project Structure

```
androidWrapper/   📱 Device info helpers
mvp/              🧱 Model, View, Presenter
remote/           🌐 API service, models, error handling
ui/activity/      🖼️ MainActivity and HomeActivity
```

---

## 🚀 Getting Started

1. 📥 Clone the repository
   ```bash
   git clone https://github.com/Aligh761/Login-App.git
   ```
2. 📂 Open the project in **Android Studio**
3. 🔧 Make sure these are set up:
   - `INTERNET` permission in `AndroidManifest.xml`
   - `viewBinding = true` in `build.gradle`
4. ▶️ Run the app on an emulator or a real device

---

## 🔄 How It Works

1. ✍️ Enter your email and tap **Send**
2. 📬 Receive a 6-digit code
3. 🔢 Enter the code and tap **Confirm**
4. 🎉 Welcome to the home screen!

---

## 📝 Notes

- 🔑 No session or token is stored yet
- 🏠 The home screen is just a placeholder
- 💡 Feel free to use this as a starting point for your own ideas

---

Made with ❤️ using Kotlin
