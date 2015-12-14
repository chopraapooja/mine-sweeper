(ns minesweeper.game-board-spec
	(:require [speclj.core :refer :all])
	(:require [minesweeper.game-board :refer :all]))

(def ^{:private true} board [[-1 0 0]		; [-1 1 0]
							 [0  0 0]		; [2  2 1]
							 [0 -1 0]])		; [1 -1 1]

(describe "open-cell"
	(it "should give bomb-signal(-1) on opening bomb cell"
    	(should= -1 (open-cell board {:row 0 :col 0} )))

	(it "should give number of bombs surround cell"
		(should= 1 (open-cell board {:row 0 :col 1} ))
    	(should= 0 (open-cell board {:row 0 :col 2} ))
    	(should= 2 (open-cell board {:row 1 :col 0} ))
    	(should= 2 (open-cell board {:row 1 :col 1} ))
    	(should= 1 (open-cell board {:row 1 :col 2} ))
    	(should= 1 (open-cell board {:row 2 :col 0} ))
    	(should= 1 (open-cell board {:row 2 :col 2} ))
    )
)

(describe "get-adjacent-cells"
	(it "should give 8 cells for center cell"
		(should= 
			#{
				{:row 0 :col 0} {:row 0 :col 1} {:row 0 :col 2} 
				{:row 1 :col 0} {:row 1 :col 2} {:row 2 :col 0} 
				{:row 2 :col 1} {:row 2 :col 2}
			}
			(get-adjacent-cells {:row 1 :col 1} board))
	)

	(it "should give 3 cells for each corner cell"
		(should= 
			#{
				{:row 0 :col 1} {:row 1 :col 0} {:row 1 :col 1}
			}
			(get-adjacent-cells {:row 0 :col 0} board))

		(should= 
			#{
				{:row 1 :col 2} {:row 1 :col 1} {:row 2 :col 1}
			}
			(get-adjacent-cells {:row 2 :col 2} board))

		(should= 
			#{
				{:row 1 :col 0} {:row 1 :col 1} {:row 2 :col 1}
			}
			(get-adjacent-cells {:row 2 :col 0} board))

		(should= 
			#{
				{:row 0 :col 1} {:row 1 :col 1} {:row 1 :col 2}
			}
			(get-adjacent-cells {:row 0 :col 2} board))
	)

	(it "should give 5 cells for each edge cell"
		(should= 
			#{
				{:row 0 :col 0} {:row 0 :col 1} {:row 1 :col 1} {:row 2 :col 0} {:row 2 :col 1}
			}
			(get-adjacent-cells {:row 1 :col 0} board))
	)
)

(describe "is-valid?"
	(it "should give true for zero row & col"
		(should= true (is-valid? board {:row 0 :col 0})))

	(it "should give true for +ve row & col"
		(should= true (is-valid? board {:row 1 :col 2})))

	(it "should give false for -ve row & col"
		(should= false (is-valid? board {:row -1 :col -1})))

	(it "should give false for -ve row or col"
		(should= false (is-valid? board {:row -1 :col 1}))
		(should= false (is-valid? board {:row 1 :col -1})))

	(it "should give false for cells outside board"
		(should= false (is-valid? board {:row 3 :col 3}))
		(should= false (is-valid? board {:row 3 :col 1}))
		(should= false (is-valid? board {:row 1 :col 3}))
	)
)


(run-specs)