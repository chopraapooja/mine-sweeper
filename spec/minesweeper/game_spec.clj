(ns minesweeper.game-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game :refer :all]))

(def board-view [ [nil nil nil] [nil nil nil] [nil nil nil] ])

(describe "create"
	
	(it "should map with get-board fn"
		(should= true (fn? (:get-board (create board-view))))
	)

	; (it "should map with get-board fn"
	; 	(should= true (fn? (:get-board (create board-view))))
	; )
)

(describe "get-board"
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-board (create board-view))))
	)
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-board (create board-view))))
	)
)

(describe "open-cell"
	(it "opens cell at given location & returns new game"
		(should= [[-1 nil nil] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 0 :col 0}))))
		(should= [[nil 1 nil] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 0 :col 1}))))
		(should= [[nil nil 0] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 0 :col 2}))))

		(should= [[nil nil nil] [2 nil nil] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 1 :col 0}))))
		(should= [[nil nil nil] [nil 2 nil] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 1 :col 1}))))
		(should= [[nil nil nil] [nil nil 1] [nil nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 1 :col 2}))))

		(should= [[nil nil nil] [nil nil nil] [1 nil nil]] ((:get-board ((:open-cell (create board-view)) {:row 2 :col 0}))))
		(should= [[nil nil nil] [nil nil nil] [nil -1 nil]] ((:get-board ((:open-cell (create board-view)) {:row 2 :col 1}))))
		(should= [[nil nil nil] [nil nil nil] [nil nil 1]] ((:get-board ((:open-cell (create board-view)) {:row 2 :col 2}))))
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

(describe "get-status"
	(it "should tell game is over on opening bomb cell"
		(def game (create board-view))
		(def game ((:open-cell game) {:row 0 :col 0}))
		(def game-status ((:get-status game)))
		(should= true (:is-over game-status))
	)
)	

(run-specs)

































