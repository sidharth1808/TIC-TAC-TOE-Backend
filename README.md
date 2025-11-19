# TIC-TAC-TOE Web Application

A beautiful, responsive Tic-Tac-Toe game built with Spring Boot backend and vanilla JavaScript frontend.

![Tic Tac Toe Screenshot](https://github.com/user-attachments/assets/9db9ab71-cf55-428b-ab96-bec0ac36e2de)

## Features

- ✨ Beautiful, modern UI with gradient design
- 🎮 Interactive game board with smooth animations
- 📱 Fully responsive - works on desktop and mobile
- 🎯 Configurable board size (3x3, 4x4, 5x5)
- 👥 Two-player mode
- 🏆 Win detection with celebration
- 🔄 New game functionality
- 🌐 RESTful API backend

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven

### Running the Application

1. **Build the project:**
   ```bash
   ./mvnw clean package
   ```

2. **Run the application:**
   ```bash
   java -jar target/TicTac-0.0.1-SNAPSHOT.jar
   ```

3. **Open your browser:**
   Navigate to `http://localhost:8080`

4. **Play the game!**
   - Enter player names and symbols
   - Choose board size (3-5)
   - Click "Start Game"
   - Click on cells to make moves

## API Endpoints

The application exposes the following REST API endpoints:

### Start a New Game
```http
POST /api/game/start
Content-Type: application/json

{
  "dimension": 3,
  "players": [
    {
      "name": "Player 1",
      "playerType": "HUMAN",
      "symbol": "X"
    },
    {
      "name": "Player 2",
      "playerType": "HUMAN",
      "symbol": "O"
    }
  ]
}
```

### Make a Move
```http
POST /api/game/{gameId}/move
Content-Type: application/json

{
  "row": 0,
  "col": 0
}
```

### Get Game State
```http
GET /api/game/{gameId}
```

## Project Structure

```
src/
├── main/
│   ├── java/com/pm/tictac/
│   │   ├── controllers/
│   │   │   ├── GameController.java          # Original game controller
│   │   │   └── TicTacToeRestController.java # REST API controller
│   │   ├── dto/                              # Data Transfer Objects
│   │   ├── models/                           # Game domain models
│   │   ├── service/
│   │   │   └── GameService.java             # Game session management
│   │   └── stratergies/                      # Game strategies
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   │   └── style.css                # Styling
│       │   ├── js/
│       │   │   └── app.js                   # Frontend logic
│       │   └── index.html                   # Main page
│       └── application.properties
```

## Technologies Used

### Backend
- Spring Boot 3.5.7
- Java 17
- Maven

### Frontend
- HTML5
- CSS3 (with modern gradients and animations)
- Vanilla JavaScript (no frameworks needed!)

## Screenshots

### Game Setup
![Setup Screen](https://github.com/user-attachments/assets/9db9ab71-cf55-428b-ab96-bec0ac36e2de)

### Active Game
![Game Board](https://github.com/user-attachments/assets/1c8c906d-2c48-46f9-83d8-c6ddf014d298)

### Winner Announcement
![Winner Screen](https://github.com/user-attachments/assets/86ba4a30-8629-497b-9d74-d53eb5934eb5)

## Development

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package -DskipTests
```

## License

This project is open source and available under the MIT License.
