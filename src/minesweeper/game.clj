(ns minesweeper.game
	(:require [minesweeper.game-board :as game-board]))

(defn did-bomb-blast? 
	[board-view]
	(contains? (set (flatten board-view)) -1))

(defn get-board [board] board)

(def ^{:private true} board [[-1 0 0]		; [-1 1 0]
							 [0  0 0]		; [2  2 1]
							 [0 -1 0]])		; [1 -1 1]

(defn won?
	[board-view]
	(let [
		number-of-opened-safe-cells 	(count (filter #(if (nil? %) false (>= % 0)) (flatten board-view) ))
		number-of-mines 				(count (filter #(if (nil? %) false (neg? %)) (flatten board) ))
		total-number-of-cells 			(count (flatten board))]
	(= number-of-opened-safe-cells (- total-number-of-cells number-of-mines))))


(defn update-row [row in-dex new-value]		; should be tested
	(map-indexed
		(fn [i actual-value] 
			(if (= i in-dex) 
				new-value
				actual-value))
		row))

(defn update-cell
	[board-view {:keys [row col] :as cell} new-value]
	(if (not (game-board/is-valid? board-view cell))
		board-view
		(map-indexed
			(fn [in-dex actual-row]
				(if (= in-dex row) 
					(update-row actual-row col new-value)
					actual-row))
			board-view)))	

(defn get-status
	[board-view]
	{
		:finished? true
		:won? true
	})

(defn create-game
	[board-view]
	{ :get-board (partial get-board board-view)
	  :open-cell (partial (fn [old-board-view cell]
	  				(create-game (update-cell old-board-view cell (game-board/open-cell board cell)))) board-view)
	  :get-status (partial get-status board-view)
	}
)

(defn open-cell 
	[board-view cell]
	(let [ cell-value (game-board/open-cell board cell)
		   updated-board (update-cell board-view cell cell-value)]
		(create-game updated-board)
		)
)