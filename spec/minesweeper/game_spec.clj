(ns minesweeper.game-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game :refer :all]))

(def board [ [nil nil nil] [nil nil nil] [nil nil nil] ])

(describe "create"
	(it "should give map"
		(should= true (map? (create board)))
	)

	(it "should map with get-status fn"
		(should= true (fn? (:get-status (create board))))
	)

	(it "should map with get-status fn"
		(should= true (fn? (:get-status (create board))))
	)
)

(describe "get-status"
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-status (create board))))
	)
	(it "should give initial status of game as all cells nil"
		(should= 
			[[nil nil nil] [nil nil nil] [nil nil nil]] 
			((:get-status (create board))))
	)
)

(run-specs)
