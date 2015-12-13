(ns minesweeper.game)

(defn get-status [board]
	board)

(defn create [board]
	{ :get-status  (partial get-status board)} )