# 🎧 PodTrack

Агрегатор подкастов с возможностью поиска, сохранения в избранное и офлайн-прослушивания.

## 📱 Текущий функционал

- ✅ **Поиск подкастов** через iTunes Search API
- ✅ **Избранное** (сохранение в локальную БД Room)
- ✅ **Кэширование** данных
- ✅ **Material Design 3** интерфейс
- ✅ **Bottom Navigation** между экранами

## 🏗 Архитектура

**MVVM + Clean Architecture**
- **Presentation**: Fragments + ViewModels (StateFlow)
- **Domain**: UseCases + модели
- **Data**: Repository, Retrofit, Room, DataStore
- **DI**: Hilt + KSP

## 🛠 Технологический стек

| Категория | Технологии |
|-----------|----------|
| **Язык** | Kotlin |
| **Build** | AGP 9.1, Gradle 8.x, KSP |
| **UI** | XML, Material 3, RecyclerView, DiffUtil |
| **Architecture** | MVVM, Repository, UseCase |
| **DI** | Hilt |
| **Network** | Retrofit, OkHttp |
| **Local DB** | Room + Flow |
| **Settings** | DataStore Preferences |
| **Navigation** | Navigation Component + Safe Args |
| **Images** | Glide |
| **Async** | Kotlin Coroutines + Flow |

## 📦 Структура проекта

app/src/main/java/com/example/podtrack/
- ├── di/ # Hilt модули
- ├── data/ # API, DAO, Repository, мапперы
- │ ├── api/
- │ ├── local/
- │ ├── mapper/
- │ └── repository/
- ├── domain/ # UseCases и модели
- │ ├── model/
- │ └── usecase/
- ├── presentation/ # UI (Fragments + ViewModels)
- │ ├── home/
- │ ├── search/
- │ ├── library/
- │ ├── base/
- │ └── settings/
- ├── worker/
- └── playback/

## 🚀 Быстрый старт

### Требования
- Android Studio Hedgehog+ (AGP 8.0+)
- JDK 21
- Android SDK 26+

### Установка
```bash
git clone <repository-url>
cd PodTrack
./gradlew build
```

### Запуск
- Откройте проект в Android Studio
- Sync Gradle
- Запустите на эмуляторе или устройстве (API 26+)

# 🎯 **Что будет реализовано в ближайшее время**
## **Экран "Поиск"**
- SearchView в Toolbar
- Debounce ввода (300ms)
- Поиск по iTunes API
- Отображение результатов в RecyclerView
- Добавление/удаление из избранного
- Обработка ошибок сети
## **Экран "Библиотека" (в разработке)**
- Список избранных подкастов из Room
## **Экран "Настройки" (заглушка)**
- Подготовка для DataStore Preferences
# 📝 Следующие шаги
- Media3/ExoPlayer для воспроизведения
- Экран плеера (Compose + XML)
- Загрузка и офлайн-прослушивание
- WorkManager для фоновой синхронизации
- Paging 3 для бесконечной прокрутки
- Детальный экран подкаста с эпизодами

## 📄 Лицензия
**MIT License**