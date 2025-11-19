const API_URL = '/api/game';
let currentGameId = null;
let currentGameState = null;

// DOM elements
const setupPanel = document.getElementById('setupPanel');
const gamePanel = document.getElementById('gamePanel');
const board = document.getElementById('board');
const gameStatus = document.getElementById('gameStatus');
const errorMsg = document.getElementById('errorMsg');

// Event listeners
document.getElementById('startGameBtn').addEventListener('click', startGame);
document.getElementById('newGameBtn').addEventListener('click', resetGame);

async function startGame() {
    const dimension = parseInt(document.getElementById('dimension').value);
    const player1Name = document.getElementById('player1Name').value.trim();
    const player1Symbol = document.getElementById('player1Symbol').value.trim().toUpperCase();
    const player2Name = document.getElementById('player2Name').value.trim();
    const player2Symbol = document.getElementById('player2Symbol').value.trim().toUpperCase();
    
    // Validation
    if (!player1Name || !player2Name) {
        showError('Please enter names for both players');
        return;
    }
    
    if (!player1Symbol || !player2Symbol || player1Symbol.length !== 1 || player2Symbol.length !== 1) {
        showError('Please enter single character symbols');
        return;
    }
    
    if (player1Symbol === player2Symbol) {
        showError('Players must have different symbols');
        return;
    }
    
    if (dimension < 3 || dimension > 5) {
        showError('Board size must be between 3 and 5');
        return;
    }
    
    const requestData = {
        dimension: dimension,
        players: [
            {
                name: player1Name,
                playerType: 'HUMAN',
                symbol: player1Symbol
            },
            {
                name: player2Name,
                playerType: 'HUMAN',
                symbol: player2Symbol
            }
        ]
    };
    
    try {
        const response = await fetch(`${API_URL}/start`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });
        
        if (!response.ok) {
            throw new Error('Failed to start game');
        }
        
        const data = await response.json();
        currentGameId = data.gameId;
        currentGameState = data;
        
        setupPanel.style.display = 'none';
        gamePanel.style.display = 'block';
        clearError();
        renderGame(data);
    } catch (error) {
        showError('Error starting game: ' + error.message);
    }
}

async function makeMove(row, col) {
    if (!currentGameId || currentGameState.gameState !== 'IN_PROGRESS') {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${currentGameId}/move`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ row, col })
        });
        
        if (!response.ok) {
            showError('Invalid move!');
            return;
        }
        
        const data = await response.json();
        currentGameState = data;
        clearError();
        renderGame(data);
    } catch (error) {
        showError('Error making move: ' + error.message);
    }
}

function renderGame(gameData) {
    // Update game status
    if (gameData.gameState === 'IN_PROGRESS') {
        gameStatus.textContent = `Current Player: ${gameData.currentPlayer}`;
        gameStatus.style.color = '#333';
    } else if (gameData.gameState === 'ENDED') {
        gameStatus.textContent = `🎉 ${gameData.winner} wins! 🎉`;
        gameStatus.style.color = '#4caf50';
    } else if (gameData.gameState === 'DRAW') {
        gameStatus.textContent = "It's a draw!";
        gameStatus.style.color = '#ff9800';
    }
    
    // Render board
    board.innerHTML = '';
    board.style.gridTemplateColumns = `repeat(${gameData.dimension}, 80px)`;
    
    for (let i = 0; i < gameData.dimension; i++) {
        for (let j = 0; j < gameData.dimension; j++) {
            const cell = gameData.board[i][j];
            const cellElement = document.createElement('div');
            cellElement.className = 'cell';
            
            if (cell.state === 'FILLED') {
                cellElement.textContent = cell.symbol;
                cellElement.classList.add('filled');
            } else if (gameData.gameState === 'IN_PROGRESS') {
                cellElement.addEventListener('click', () => makeMove(i, j));
            }
            
            board.appendChild(cellElement);
        }
    }
}

function resetGame() {
    currentGameId = null;
    currentGameState = null;
    setupPanel.style.display = 'block';
    gamePanel.style.display = 'none';
    clearError();
}

function showError(message) {
    errorMsg.textContent = message;
}

function clearError() {
    errorMsg.textContent = '';
}
