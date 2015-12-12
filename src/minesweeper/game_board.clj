(ns minesweeper.game-board)

(defn open-cell [board & {:keys [row col]}]
	(nth (nth board row) col))
