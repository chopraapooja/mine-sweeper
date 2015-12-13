(ns minesweeper.game-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game :refer :all]))

(def board-view [ [nil nil nil] [nil nil nil] [nil nil nil] ])

(describe "create"
	(it "should give map"
		(should= true (map? (create board-view)))
	)

	(it "should map with get-status fn"
		(should= true (fn? (:get-status (create board-view))))
	)

	(it "should map with get-status fn"
		(should= true (fn? (:get-status (create board-view))))
	)
)

(describe "get-status"
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-status (create board-view))))
	)
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-status (create board-view))))
	)
)

(describe "open-cell"
	(it "opens cell at given location & returns new board-view"
		(should= [[nil 1 nil] [nil nil nil] [nil nil nil]] (:get-status (:open-cell (create board-view))))
	)
)

(describe "update-cell"
	(it "updates cell at given location & returns new board-view"
		(should= [[-1 nil nil] [nil nil nil] [nil nil nil]] (update-cell board-view {:row 0 :col 0} -1))
	)

	(it "should give back same board-view when cell location is not valid"
		(should= [[nil nil nil] [nil nil nil] [nil nil nil]] (update-cell board-view {:row 3 :col 3} -1))
	)

)

(run-specs)
