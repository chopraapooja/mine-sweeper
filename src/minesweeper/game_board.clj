(ns minesweeper.game-board)
 

 
(defn open-cell [board & {:keys [row col]}]
	(if (= row 0) 1 -1))
