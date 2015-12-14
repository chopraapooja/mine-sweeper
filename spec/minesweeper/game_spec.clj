(ns minesweeper.game-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game :refer :all]))

(def board-view [ [nil nil nil] [nil nil nil] [nil nil nil] ])

(describe "create-game"
	
	(it "should map with get-board fn"
		(should= true (fn? (:get-board (create-game board-view))))
	)

	(it "should map with open-cell fn"
		(should= true (fn? (:open-cell (create-game board-view))))
	)

	(it "should map with get-status fn"
		(should= true (fn? (:get-status (create-game board-view))))
	)
)

(describe "get-board"
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-board (create-game board-view))))
	)
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-board (create-game board-view))))
	)
)

(describe "open-cell"
	(it "opens cell at given location & returns new game"
		(should= [[-1 nil nil] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 0 :col 0}))))
		(should= [[nil 1 nil] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 0 :col 1}))))
		(should= [[nil nil 0] [nil nil nil] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 0 :col 2}))))

		(should= [[nil nil nil] [2 nil nil] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 1 :col 0}))))
		(should= [[nil nil nil] [nil 2 nil] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 1 :col 1}))))
		(should= [[nil nil nil] [nil nil 1] [nil nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 1 :col 2}))))

		(should= [[nil nil nil] [nil nil nil] [1 nil nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 2 :col 0}))))
		(should= [[nil nil nil] [nil nil nil] [nil -1 nil]] ((:get-board ((:open-cell (create-game board-view)) {:row 2 :col 1}))))
		(should= [[nil nil nil] [nil nil nil] [nil nil 1]] ((:get-board ((:open-cell (create-game board-view)) {:row 2 :col 2}))))
	)

	(it "should not open-cell when game is over"
		(should= [[-1 nil nil] [nil nil nil] [nil nil nil]]
			((:get-board ((:open-cell (create-game board-view)) {:row 0 :col 0}))))
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
	(it "should tell game is not finished & not won"
		(def game (create-game board-view))
		(def game-status ((:get-status game)))
		(should= false (:finished? game-status))
		(should= false (:won? game-status))
	)

	(it "should tell game is over on opening bomb cell"
		(def game (create-game board-view))
		(def game ((:open-cell game) {:row 0 :col 0}))
		(def game-status ((:get-status game)))
		(should= true (:finished? game-status))
		(should= false (:won? game-status))
	)

	(it "should tell you won the game"
		(def game (create-game board-view))
		(def game ((:open-cell game) {:row 0 :col 1}))
		(def game ((:open-cell game) {:row 0 :col 2}))
		(def game ((:open-cell game) {:row 1 :col 0}))
		(def game ((:open-cell game) {:row 1 :col 1}))
		(def game ((:open-cell game) {:row 1 :col 2}))
		(def game ((:open-cell game) {:row 2 :col 0}))
		(def game ((:open-cell game) {:row 2 :col 2}))
		(def game-status ((:get-status game)))
		(should= true (:finished? game-status))
		(should= true (:won? game-status))
	)
	(it "should tell game is not finished in the middle of game`"
		(def game (create-game board-view))
		(def game ((:open-cell game) {:row 0 :col 1}))
		(def game-status ((:get-status game)))
		(should= false (:finished? game-status))
		(should= false (:won? game-status))
	)

)

(describe "did-bomb-blast?"
	(it "should give true when board has -1"
		(def board-with-bomb-blast (update-cell board-view {:row 0 :col 1} -1))
		(should= true (did-bomb-blast? board-with-bomb-blast))

	(it "should give false when board does not have -1"
		(should= false (did-bomb-blast? board-view)))))

(describe "won?"
	(it "should give true when all safe cells are opened"
		(should= true (won? [[nil 1 0] [2 2 1] [1 nil 1]])))

	(it "should give false when all safe cells are not opened"
		(should= false (won? [[nil 1 0] [2 nil 1] [1 nil 1]])))

	(it "should give true when all safe cells are opened"
		(should= false (won? [[-1 nil 0] [2 2 1] [1 nil 1]]))))

(run-specs)

































