(ns minesweeper.game-board)
 
(defn get-board []
	[[1 1] [1 -1]])

(defn open-cell [row-col] 
	(nth 
		(nth (get-board) (:row row-col)) 
	(:col row-col)))