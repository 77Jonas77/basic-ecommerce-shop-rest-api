## 🛒 E-commerce Order Management API

**Technologie:** Spring Boot, PostgreSQL, JWT, REST API, JPA/Hibernate

### 🎯 Cel projektu

Celem projektu jest stworzenie prostego, ale funkcjonalnego API do zarządzania zamówieniami w sklepie e-commerce. Projekt ma na celu rozwój umiejętności backendowych, w szczególności w zakresie modelowania danych, uwierzytelniania użytkowników oraz implementacji operacji CRUD.

### 🔐 Uwierzytelnianie

- Użytkownik może zarejestrować się, zalogować, wylogować z systemu.
- Autoryzacja z rolami: Administrator, Sprzedawca, Kupujący
- System generuje token Session ID, który będzie wykorzystywany do uwierzytelnienia oraz autoryzacji dostępu do chronionych zasobów.
- Podstawowa konfiguracja Spring Security, w której zaimplementowany został własny UserDetailsService w związku z wykorzystaniem UsernamePasswordAuthenticationToken służący do autentykacji użytkownika.

### 📦 Funkcjonalności API

Use case'y:

- Rejestrowanie, logowanie, wylogowanie użytkownika.
- Endpoint GET na pobranie wszystkich produktów z wykorzystniem interfejsu Pageable z możliwym posortowaniem.
- (POST) Dodanie/ utworzenie produktu
- (DELETE) Usunięcie produktu
- Aktualizacja produktu (PATCH)
- Pobranie oraz wczytanie pliku z wykorzystniem interfejsu MultipartFile
- Pobranie użytkownika z wykorzystniem ID
- Aktualizacja roli użytkownika

Inne:

- Custom Rate Limiter zrealizowany za pomocą OncePerRequestFilter
- Exception handler zrealizowany za pomocą globalnego @ControllerAdvice
- SessionDebugFilter, który służy do debugowania / rejestrowania informacji przychodzącego request'a.
- Mappery, konwertery dla enumów.

### Mniej znaczące decyzje

- Architektura struktury katalogów naśladująca konwencję _Screaming architecture_
- Staranność utrzymania dobrych technik programistycznych (kierowałem się natomiast raczej "dowiezieniem i działaniem" niż dążenie do idealnego kodu):
  - YAGNI
  - KISS
  - SOLID
  - DRY
- API versioning

### 🧱 Technologie i biblioteki

- Spring Boot (Java)
- PostgreSQL
- Docker
- Spring Data JPA
- Spring Security
- JUnit 5, Mockito, Testcontainers do testów jednostkowych oraz integracyjnych

### Roadmapa

Sugerowałem się: 

<img width="1404" height="929" alt="SPRING BOOT ROADMAP" src="https://github.com/user-attachments/assets/46e04785-a0e8-4430-a714-3f8282646b96" />

### Co można było zrobić lepiej? - dalszy potencjalny rozwój

Celem tego projektu było poznanie nowych dla mnie możliwości frameworku jakim jest spring boot oraz utrwaleniem podstaw jego działania oraz wstępna praca z PostgreSQL. Elementów do poprawy na pewno jest więcej natomiast moim zdaniem najważniejsze:

- Pobieżna analiza biznesowa realizowanego problemu - pewne konstrukcje, rozwiązania są bardzo uproszczone i nie rozwiązują realnego problemu biznesowego.
- Przechowywanie sesji - jako dalszy rozwój, np. za pomocą Redis.
- Rate Limiter/ Throtling z wykorzystniem algorytmu kubełkowego, np. Bucket4j
- Integracja z API do płatności.
- Zwiększenie security, np. poprzez wykorzystanie JWT (dyskusyjne).
- Architektura rozwiązania - zapewne, natomiast jest to obszar wiedzy, który jeszcze muszę zgłębić, aby podejmować odpowiednie decyzje.
- Testy - zdecydowanie można poprawić sposób realizowania testów - na ten moment są to basic testy, które służyły mi za przykłady w celu zrozumienia koncepcji stojącej za tym obszarem.
