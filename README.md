# File Hash Checker

> A simple JavaFX desktop application to generate and compare file hashes — useful for verifying the integrity of downloaded files.

![Java](https://img.shields.io/badge/Java-8%2B-orange?style=flat-square&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-GUI-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)
![Status](https://img.shields.io/badge/Status-Pre--Beta-yellow?style=flat-square)

---

## About

File Hash Checker is a lightweight desktop utility built with JavaFX that lets you quickly generate cryptographic hashes for any file and compare them against a known hash — perfect for confirming that a downloaded file hasn't been corrupted or tampered with.

---

## Features

- Generate file hashes using popular algorithms:
  - **MD5**
  - **CRC32**
  - *(and more)*
- Compare a generated hash against an expected hash value
- Simple, clean GUI built with JavaFX
- Cross-platform support (Windows, macOS, Linux)

---

## Screenshots

> *(Screenshots coming soon)*

---

## Requirements

- **Java 8** or higher (with JavaFX bundled or available)
- Compatible with Windows, macOS, and Linux

---

## Getting Started

### Option 1: Run the Pre-built Release

1. Navigate to the [`releases/pre-beta`](releases/pre-beta) folder.
2. Download the JAR or executable for your platform.
3. Run it:
   ```bash
   java -jar FileHashChecker.jar
   ```

### Option 2: Build from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/ishan-s/File-Hash-Checker.git
   cd File-Hash-Checker
   ```

2. Open the project in your preferred Java IDE (Eclipse, IntelliJ, etc.).

3. Ensure JavaFX is on your build path (see `lib/` folder and `.classpath`).

4. Build using the provided `build.fxbuild` configuration, or export a runnable JAR from your IDE.

---

## Usage

1. Launch the application.
2. Click **Browse** (or drag and drop) to select a file.
3. Choose your desired hash algorithm (e.g. MD5, CRC32).
4. Click **Generate** to compute the hash.
5. Optionally, paste an expected hash value into the comparison field and click **Compare** to verify integrity.

---

## Project Structure

```
File-Hash-Checker/
├── src/                  # Java source files
├── lib/                  # External libraries / dependencies
├── docs/                 # Documentation assets
├── releases/pre-beta/    # Pre-built release binaries
├── .settings/            # IDE project settings (Eclipse)
├── build.fxbuild         # JavaFX build configuration
├── .classpath            # Eclipse classpath
└── .project              # Eclipse project file
```

---

## Known Issues / Planned Improvements

- [ ] Add a loading spinner during hash generation for large files ([#1](https://github.com/ishan-s/File-Hash-Checker/issues/1))
- [ ] Move hash computation to a background thread to prevent UI freezing ([#2](https://github.com/ishan-s/File-Hash-Checker/issues/2))
- [ ] Add a status bar with progress feedback (e.g. "Generating...")

---

## Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m 'Add my feature'`
4. Push to the branch: `git push origin feature/my-feature`
5. Open a pull request

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

**Ishan Shrivastava** — [@ishan-s](https://github.com/ishan-s)
