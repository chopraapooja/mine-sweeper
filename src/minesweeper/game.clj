(ns minesweeper.game
	(:require [minesweeper.game-board :as game-board]))

(defn get-board [board] board)

(def ^{:private true} board [[-1 0 0]		; [-1 1 0]
							 [0  0 0]		; [2  2 1]
							 [0 -1 0]])		; [1 -1 1]

(defn update-row [row in-dex new-value]
	(map-indexed (fn [i x] (if (= i in-dex) new-value x)) row)
)

(defn update-cell [board-view {:keys [row col] :as cell} new-value]
	(if (not (game-board/is-valid? board-view cell))
		board-view
		(map-indexed (fn [i r] (if (= i row) (update-row r col new-value) r)) board-view))
)


(defn get-status [board-view] { :is-over true })

(defn create [board-view]
	{ :get-board (partial get-board board-view)
	  :open-cell (partial (fn [old-board-view cell]
	  				(create (update-cell old-board-view cell (game-board/open-cell board cell)))) board-view)
	  :get-status (partial get-status board-view)
	}
)

(defn open-cell [board-view cell]
	(create (update-cell board-view cell (game-board/open-cell board cell) ))
)