(ns minesweeper.game-board)

(defn is-bomb? [board {:keys [row col] }]
	(= -1 (nth (nth board row) col)))

(defn is-valid? [board {:keys [row col]}]
	(if (and (<= 0 row) (> (count board) row) (<= 0 col) (> (count board) col)) true false))

(defn surrounding-cells [ {:keys [row col]} board]
	(let [is-valid-partial? (partial is-valid? board)]
		(set (filter is-valid-partial? [ 		
				{:row (dec row) 	:col (dec col)}
				{:row (dec row) 	:col col}
				{:row (dec row) 	:col (inc col)}
				{:row row 			:col (dec col)}
				{:row row 			:col (inc col)}
				{:row (inc row)		:col (dec col)}
				{:row (inc row)		:col col}
				{:row (inc row)		:col (inc col)} 
		]))))

(defn open-cell [board {:keys [row col] :as cell}]
	(let [ cell-value (nth (nth board row) col)
			is-bomb-partial? (partial is-bomb? board)]
		(if (neg? cell-value)
			cell-value
			(count (filter is-bomb-partial? (surrounding-cells cell board))))))

