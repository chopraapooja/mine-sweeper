(ns minesweeper.game-board)

(defn cell-value [board {:keys [row col]}]
	(nth (nth board row) col))

(defn is-bomb?
	[board cell]
	(= -1 (cell-value board cell)))

(defn is-valid?
	[board {:keys [row col]}]
	(let [total-rows (count board)]
	(and (contains? (set (range total-rows)) row)
		 (contains? (set (range total-rows)) col) )))

(defn get-adjacent-cells [ {:keys [row col]} board]
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
	(let [ cell-value (cell-value board cell)
			is-bomb-partial? (partial is-bomb? board)]
		(if (neg? cell-value)
			cell-value
			(count (filter is-bomb-partial? (get-adjacent-cells cell board))))))

