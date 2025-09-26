# Freelook++

A client-side Fabric mod that enhances the freelook experience in Minecraft with improved camera controls and features.

## Features

- **Hold to Freelook**: Hold Left Alt to temporarily enable freelook mode
- **Toggle Freelook**: Press F6 to toggle freelook mode on/off
- **Configuration Screen**: Press F7 to access comprehensive settings
- **Smooth Camera Movement**: Enhanced camera controls with configurable sensitivity
- **Customizable Settings**: Adjust sensitivity, invert Y-axis, and more
- **Client-Side Only**: No server-side dependencies required
- **Lightweight**: Minimal performance impact

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download the latest release of Freelook++
3. Place the mod file in your `mods` folder
4. Launch Minecraft with the Fabric profile

## Controls

| Key | Action |
|-----|--------|
| Left Alt (Hold) | Temporary freelook mode |
| F6 | Toggle freelook mode |
| F7 | Open configuration screen |

**Note**: Keys can be customized in Options > Controls > Freelook++ category.

## Configuration

The mod includes a comprehensive configuration system accessible via the F7 key or through the Controls menu:

- **Mouse Sensitivity**: Adjust mouse sensitivity for freelook (0.1x to 3.0x)
- **Invert Y Axis**: Invert vertical mouse movement
- **Smooth Transition**: Enable smooth camera transitions
- **Auto Third Person**: Automatically switch to third person when freelook is enabled

**Configuration File**: Settings are automatically saved to `.minecraft/config/freelookplusplus.json`

## Compatibility

- **Minecraft Version**: 1.21.8
- **Fabric Loader**: 0.17.2 or higher
- **Java**: 21 or higher
- **Environment**: Client-side only

## Building from Source

1. Clone this repository:
   ```bash
   git clone https://github.com/BloodredX/FreelookPlusPlus.git
   cd FreelookPlusPlus
   ```
2. Run `./gradlew build`
3. Find the compiled mod in `build/libs/`

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.