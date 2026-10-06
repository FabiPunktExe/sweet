# Sweet

Sweet is a Compose Multiplatform library.
On non-web platforms (JVM, Android) sweet is a wrapper for the `androidx.`-components.
On web platforms (JS, Wasm JS) DOM elements are used to draw the components instead of a canvas.


## Supported Platforms
- JVM
- Android
- JS
- Wasm JS


## Modules

| Module | Description |
|---|---|
| `ui` | Basic UI building blocks |
| `foundation-layout` | Layout components: (`Row`, `Column`, `Box`) |
| `material3` | Material 3 components |
| `example` | Shared example app code |
| `example-jvm` / `example-js` / `example-wasm` | Platform entry points for the example app |


## Build & Run

The project uses [Kotlin Toolchain](https://github.com/JetBrains/amper) (no Gradle).

```bash
# Build all modules
./kotlin build

# Run the example app
./kotlin run --module example-jvm   # Desktop (JVM)
./kotlin run --module example-wasm  # Browser (Wasm)
./kotlin run --module example-js    # Browser (JS)
```


## Example

```kotlin
Theme(colorScheme(Color(0xAAFF88))) {
    Surface {
        var count by remember { mutableStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Clicked $count times")
        }
    }
}
```


## License

[MIT](LICENSE) © Fabi.exe