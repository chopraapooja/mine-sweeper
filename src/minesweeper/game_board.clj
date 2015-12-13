(ns minesweeper.game-board)


(defn open-cell [board & {:keys [row col] :as cell}]
	(let [ cell-value (nth (nth board row) col)]
		(if (neg? cell-value)
			cell-value)))


(defn surrounding-cells [cell])
