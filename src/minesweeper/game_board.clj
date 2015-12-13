(ns minesweeper.game-board)

(defn open-cell [board & {:keys [row col] :as cell}]
	(let [ cell-value (nth (nth board row) col)]
		(if (neg? cell-value)
			cell-value)))

(defn is-valid? [ {:keys [row col]} ] 
	(if (and (<= 0 row) (<= 0 col)) true false))

(defn surrounding-cells [ {:keys [row col]} ] 
	(filter is-valid? [ 		
			{:row (dec row) 	:col (dec col)}
			{:row (dec row) 	:col col}
			{:row (dec row) 	:col (inc col)}
			{:row row 			:col (dec col)}
			{:row row 			:col (inc col)}
			{:row (inc row)		:col (dec col)}
			{:row (inc row)		:col col}
			{:row (inc row)		:col (inc col)} 
	]))
