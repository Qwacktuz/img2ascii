# img2ascii

A CLI tool written in Kotlin that converts images (JPG, PNG) into ASCII art directly in your terminal.

## Installation

### Prerequisites

- Java 25 or higher must be installed.
  - I recommend [Adoptium Temurin 25](https://adoptium.net/temurin/releases/?version=25&os=any&arch=any).
  - Check your version by running: `java -version`

Install script coming soon™️

## Usage

```bash
java -jar img2ascii.jar <path-to-image> <width>
```

### Build from Source

```bash
git clone https://github.com/Qwacktuz/img2ascii.git
cd img2ascii

# MacOS/Linux
./gradlew build 

# Windows
./gradlew.bat build 
```

## License

This project is licensed under the [MIT License](LICENSE).
